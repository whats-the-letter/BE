package com.dearnewyear.dny.music.controller;

import com.dearnewyear.dny.music.dto.request.AddMusicRequest;
import com.dearnewyear.dny.music.dto.response.MusicListResponse;
import com.dearnewyear.dny.music.service.MusicService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    @GetMapping("/list")
    public ResponseEntity<MusicListResponse> getMusicList() {
        MusicListResponse response = musicService.getMusicList();
        return ResponseEntity.ok(response);
    }

    @PostMapping("/add")
    public ResponseEntity<String> addMusic(@ModelAttribute @Valid AddMusicRequest addMusicRequest) {
        musicService.addMusic(addMusicRequest);
        return ResponseEntity.status(201).body("음악 추가 성공");
    }
}
