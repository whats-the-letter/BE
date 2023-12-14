package com.dearnewyear.dny.album.controller;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.dto.response.AlbumResponse;
import com.dearnewyear.dny.album.dto.response.CollectionResponse;
import com.dearnewyear.dny.album.service.AlbumService;
import com.dearnewyear.dny.common.error.exception.CustomException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
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
            @io.swagger.annotations.ApiResponse(code = 400, message = "앨범 보내기 권한 없거나 유효성 검사 실패"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "앨범 보내기 실패")
    })
    @PostMapping("/send")
    public ResponseEntity<AlbumResponse> sendAlbum(@ModelAttribute @Valid AlbumRequest albumRequest, HttpServletRequest request) {
        try {
            AlbumInfo albumInfo = albumService.sendAlbum(albumRequest, request);
            return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new AlbumResponse(null, e.getMessage()));
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

    @ApiOperation(value = "컬렉션 조회")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "컬렉션 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "컬렉션 조회 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "컬렉션 조회 실패")
    })
    @GetMapping("/collection")
    public ResponseEntity<CollectionResponse> viewCollection(HttpServletRequest request) {
        try {
            List<AlbumInfo> collection = albumService.viewCollection(request);
            return ResponseEntity.ok(new CollectionResponse(collection, null));
        } catch (CustomException e) {
            return ResponseEntity.status(e.getErrorCode().getStatus()).body(new CollectionResponse(null, e.getMessage()));
        }
    }
}
