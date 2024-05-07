package com.dearnewyear.dny.album.service;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.repository.AlbumRepository;
import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.CustomException;
import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.common.service.S3Service;
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.repository.MusicRepository;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final JwtTokenProvider jwtTokenProvider;
    private final S3Service s3Service;
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;

    public AlbumInfo sendAlbum(AlbumRequest albumRequest, HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessToken(request);

        User fromUser = findUserById(jwtTokenProvider.getUserId(accessToken));
        User toUser = findUserByIdOrNull(albumRequest.getToUserId());
        Music music = findMusicById(albumRequest.getMusicId());

        String frontImgName = albumRequest.getFrontImage() != null ?
                s3Service.upload(albumRequest.getFrontImage()) : null;
        String backImgName = albumRequest.getBackImage() != null ?
                s3Service.upload(albumRequest.getBackImage()) : null;

        Album album = Album.builder()
                .albumCover(albumRequest.getAlbumCover())
                .albumPhrases(albumRequest.getAlbumPhrases())
                .albumBackground(albumRequest.getAlbumBackground())
                .musicId(music.getMusicId())
                .fromUserId(fromUser.getUserId())
                .toUserId(toUser != null ? toUser.getUserId() : null)
                .fromName(albumRequest.getFromName())
                .toName(albumRequest.getToName())
                .letter(albumRequest.getLetter())
                .frontImage(frontImgName)
                .backImage(backImgName)
                .build();
        albumRepository.save(album);
        return new AlbumInfo(album, music);
    }

    public AlbumInfo viewAlbum(String albumId, HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessToken(request);

        Album album = findAlbumById(albumId);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        checkAlbumPermission(album, authentication);

        return new AlbumInfo(album, findMusicById(album.getMusicId()));
    }

    public List<AlbumInfo> viewCollection(HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessToken(request);

        User user = findUserById(jwtTokenProvider.getUserId(accessToken));
        List<Album> albumList = albumRepository.findByToUserId(user.getUserId());
        return albumList.stream()
                .map(album -> new AlbumInfo(album, findMusicById(album.getMusicId())))
                .collect(Collectors.toList());
    }

    public void addAlbumToCollection(String albumId, HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessToken(request);

        Album album = findAlbumById(albumId);
        if (album.getToUserId() != null)
            throw new CustomException(ErrorCode.ALBUM_NOT_AUTHORIZED);

        User user = findUserById(jwtTokenProvider.getUserId(accessToken));
        album.updateTo(user.getUserId());
        albumRepository.save(album);
    }

    private void checkAlbumPermission(Album album, Authentication authentication) {
        if (album.getToUserId() == null)
            return;

        User fromUser = findUserById(album.getFromUserId());
        User toUser = findUserByIdOrNull(album.getToUserId());

        if (!isFromUser(fromUser, authentication) && (toUser != null && !isToUser(toUser,
                authentication)))
            throw new CustomException(ErrorCode.ALBUM_NOT_AUTHORIZED);
    }

    private boolean isFromUser(User fromUser, Authentication authentication) {
        return fromUser.getUserName().equals(authentication.getName());
    }

    private boolean isToUser(User toUser, Authentication authentication) {
        return toUser.getUserName().equals(authentication.getName());
    }

    public User findUserById(String userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }

    public User findUserByIdOrNull(String userId) {
        return userId != null ? findUserById(userId) : null;
    }

    public Music findMusicById(String musicId) {
        return musicRepository.findById(musicId)
                .orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));
    }

    public Album findAlbumById(String albumId) {
        return albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(ErrorCode.ALBUM_NOT_FOUND));
    }
}
