package com.dearnewyear.dny.album.controller;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.dto.response.AlbumResponse;
import com.dearnewyear.dny.album.service.AlbumService;
import com.dearnewyear.dny.common.error.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Album"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/album")
public class AlbumController {

    private final AlbumService albumService;

    @ApiOperation(value = "앨범 보내기")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "앨범 보내기 성공"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "앨범 보내기 실패")
    })
    @PostMapping("/send")
    public ResponseEntity<String> sendAlbum(@ModelAttribute AlbumRequest albumRequest, HttpServletRequest request) {
        try {
            albumService.sendAlbum(albumRequest, request);
            return ResponseEntity.ok("앨범 보내기 성공");
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(e.getMessage());
        }
    }

    @ApiOperation(value = "앨범 조회")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "앨범 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "앨범 조회 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "앨범 조회 실패")
    })
    @GetMapping("/view/{albumId}")
    public ResponseEntity<AlbumResponse> viewAlbum(@PathVariable Long albumId, HttpServletRequest request) {
        try {
            AlbumInfo albumInfo = albumService.viewAlbum(albumId, request);
            return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new AlbumResponse(null, e.getMessage()));
        }
    }
}
