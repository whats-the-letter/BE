package com.dearnewyear.dny.music.controller;

import com.dearnewyear.dny.music.dto.request.AddMusicRequest;
import com.dearnewyear.dny.music.dto.response.MusicListResponse;
import com.dearnewyear.dny.music.service.MusicService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Music"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/music")
public class MusicController {

    private final MusicService musicService;

    @ApiOperation(value = "음악 목록 조회")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "음악 목록 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "음악 목록 조회 실패")
    })
    @GetMapping("/list")
    public ResponseEntity<MusicListResponse> getMusicList() {
        MusicListResponse response = musicService.getMusicList();
        return ResponseEntity.ok(response);
    }

    @ApiOperation(value = "음악 추가")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 201, message = "음악 추가 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "카테고리 유효성 검사 실패"),
            @io.swagger.annotations.ApiResponse(code = 500, message = "음악 추가 실패")
    })
    @PostMapping("/add")
    public ResponseEntity<String> addMusic(@ModelAttribute @Valid AddMusicRequest addMusicRequest) {
        musicService.addMusic(addMusicRequest);
        return ResponseEntity.status(201).body("음악 추가 성공");
    }
}
