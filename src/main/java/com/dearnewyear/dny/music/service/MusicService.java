package com.dearnewyear.dny.music.service;

import com.dearnewyear.dny.common.error.exception.CustomException;
import com.dearnewyear.dny.common.service.S3Service;
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.dto.MusicInfo;
import com.dearnewyear.dny.music.dto.request.AddMusicRequest;
import com.dearnewyear.dny.music.dto.response.MusicListResponse;
import com.dearnewyear.dny.music.repository.MusicRepository;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;
    private final S3Service s3Service;

    public MusicListResponse getMusicList() {
        List<MusicInfo> musicList = musicRepository.findAll()
                .stream()
                .map(MusicInfo::new)
                .toList();
        return new MusicListResponse(musicList, null);
    }

    public void addMusic(AddMusicRequest addMusicRequest) {
        String thumbnailUrl = null;
        try {
            thumbnailUrl = s3Service.upload(addMusicRequest.getThumbnail());
            Music music = new Music(addMusicRequest, thumbnailUrl);
            musicRepository.save(music);
        } catch (CustomException e) {
            throw new CustomException(e.getErrorCode());
        }
    }
}
