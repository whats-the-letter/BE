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
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.repository.MusicRepository;
import com.dearnewyear.dny.user.domain.User;
import com.dearnewyear.dny.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class AlbumService {

    private final UserRepository userRepository;
    private final AlbumRepository albumRepository;
    private final MusicRepository musicRepository;

    public void sendAlbum(AlbumRequest request) {
        User fromUser = userRepository.findById(request.getFromUserId())
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        User toUser = null;
        if (request.getToUserId() != null)
            toUser = userRepository.findById(request.getToUserId())
                    .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
        Music music = musicRepository.findById(request.getMusicId())
                .orElseThrow(() -> new CustomException(ErrorCode.MUSIC_NOT_FOUND));

        Album album = Album.builder()
                .albumCover(AlbumCover.valueOf(request.getAlbumCover()))
                .albumPhrases(AlbumPhrases.valueOf(request.getAlbumPhrases()))
                .albumBackground(AlbumBackground.valueOf(request.getAlbumBackground()))
                .music(music)
                .fromUser(fromUser)
                .toUser(toUser)
                .fromName(request.getFromName())
                .toName(request.getToName())
                .letter(request.getLetter())
                .build();
        albumRepository.save(album);
    }

    public AlbumInfo viewAlbum(Long albumId) {
        Album album = albumRepository.findById(albumId)
                .orElseThrow(() -> new CustomException(ErrorCode.ALBUM_NOT_FOUND));
        return AlbumInfo.builder()
                .albumId(album.getAlbumId())
                .albumCover(String.valueOf(album.getAlbumCover()))
                .albumPhrases(String.valueOf(album.getAlbumPhrases()))
                .albumBackground(String.valueOf(album.getAlbumBackground()))
                .musicName(album.getMusic().getMusicName())
                .musicArtist(album.getMusic().getMusicArtist())
                .youtubeUrlId(album.getMusic().getYoutubeUrlId())
                .fromName(album.getFromName())
                .toName(album.getToName())
                .letter(album.getLetter())
                .build();
    }
}
