package com.dearnewyear.dny.music.service;

import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.dto.MusicInfo;
import com.dearnewyear.dny.music.dto.request.AddMusicRequest;
import com.dearnewyear.dny.music.dto.response.MusicListResponse;
import com.dearnewyear.dny.music.repository.MusicRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MusicService {

    private final MusicRepository musicRepository;

    public MusicListResponse getMusicList() {
        List<MusicInfo> musicList = musicRepository.findAll()
                .stream()
                .map(MusicInfo::new)
                .collect(Collectors.toList());
        return new MusicListResponse(musicList, null);
    }

    public void addMusic(AddMusicRequest addMusicRequest) {
        Music music = new Music(addMusicRequest);
        musicRepository.save(music);
    }
}
