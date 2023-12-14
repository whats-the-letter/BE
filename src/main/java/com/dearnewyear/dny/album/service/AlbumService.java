package com.dearnewyear.dny.album.service;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.album.domain.constant.AlbumBackground;
import com.dearnewyear.dny.album.domain.constant.AlbumCover;
import com.dearnewyear.dny.album.domain.constant.AlbumPhrases;
import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.repository.AlbumRepository;
import com.dearnewyear.dny.common.error.ErrorCode;
import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.common.jwt.JwtTokenProvider;
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.repository.MusicRepository;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.UserRepository;
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
    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;

    public AlbumInfo sendAlbum(AlbumRequest albumRequest, HttpServletRequest request) {
        String accessToken = jwtTokenProvider.getAccessToken(request);
        User fromUser = userRepository.findById(jwtTokenProvider.getUserId(accessToken))
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Long toUserId = albumRequest.getToUserId();
        User toUser = null;
        if (toUserId != null)
            toUser = userRepository.findById(toUserId)
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));

        Music music = musicRepository.findById(albumRequest.getMusicId())
                .orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));

        Album album = Album.builder()
                .albumCover(AlbumCover.valueOf(albumRequest.getAlbumCover()))
                .albumPhrases(AlbumPhrases.valueOf(albumRequest.getAlbumPhrases()))
                .albumBackground(AlbumBackground.valueOf(albumRequest.getAlbumBackground()))
                .music(music)
                .fromUser(fromUser)
                .toUser(toUser)
                .fromName(albumRequest.getFromName())
                .toName(albumRequest.getToName())
                .letter(albumRequest.getLetter())
                .build();
        albumRepository.save(album);
        return new AlbumInfo(album);
    }

    public AlbumInfo viewAlbum(Long albumId, HttpServletRequest request) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(ErrorCode.ALBUM_NOT_FOUND));

        String accessToken = jwtTokenProvider.getAccessToken(request);
        Authentication authentication = jwtTokenProvider.getAuthentication(accessToken);
        checkAlbumPermission(album, authentication);

        return new AlbumInfo(album);
    }

    private void checkAlbumPermission(Album album, Authentication authentication) {
        if (album.getToUser() == null)
            return;

        User fromUser = album.getFromUser();
        User toUser = album.getToUser();

        if (!isFromUser(fromUser, authentication) && !isToUser(toUser, authentication))
            throw new CustomException(ErrorCode.ALBUM_NOT_AUTHORIZED);
    }

    private boolean isFromUser(User fromUser, Authentication authentication) {
        return fromUser.getUserName().equals(authentication.getName());
    }

    private boolean isToUser(User toUser, Authentication authentication) {
        return toUser.getUserName().equals(authentication.getName());
    }
}
