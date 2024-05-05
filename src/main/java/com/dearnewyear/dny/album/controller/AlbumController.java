package com.dearnewyear.dny.album.controller;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.dto.response.AlbumResponse;
import com.dearnewyear.dny.album.dto.response.CollectionResponse;
import com.dearnewyear.dny.album.service.AlbumService;
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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"Album"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/album")
public class AlbumController {

    private final AlbumService albumService;

    @ApiOperation(value = "앨범 보내기")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "앨범 보내기 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "앨범 보내기 권한 없거나 유효성 검사 실패"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "앨범 보내기 실패")
    })
    @PostMapping("/send")
    public ResponseEntity<AlbumResponse> sendAlbum(@ModelAttribute @Valid AlbumRequest albumRequest,
            HttpServletRequest request) {
        AlbumInfo albumInfo = albumService.sendAlbum(albumRequest, request);
        return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
    }

    @ApiOperation(value = "앨범 조회")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "앨범 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "앨범 조회 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "앨범 조회 실패")
    })
    @GetMapping("/view/{albumId}")
    public ResponseEntity<AlbumResponse> viewAlbum(@PathVariable String albumId,
            HttpServletRequest request) {
        AlbumInfo albumInfo = albumService.viewAlbum(albumId, request);
        return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
    }

    @ApiOperation(value = "컬렉션 조회")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "컬렉션 조회 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "컬렉션 조회 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "컬렉션 조회 실패")
    })
    @GetMapping("/collection")
    public ResponseEntity<CollectionResponse> viewCollection(HttpServletRequest request) {
        List<AlbumInfo> collection = albumService.viewCollection(request);
        return ResponseEntity.ok(new CollectionResponse(collection, null));
    }

    @ApiOperation(value = "내 컬렉션에 추가")
    @ApiResponses({
            @io.swagger.annotations.ApiResponse(code = 200, message = "컬렉션에 추가 성공"),
            @io.swagger.annotations.ApiResponse(code = 400, message = "컬렉션에 추가 권한 없음"),
            @io.swagger.annotations.ApiResponse(code = 404, message = "컬렉션에 추가 실패")
    })
    @PutMapping("/collection/{albumId}")
    public ResponseEntity<String> addCollection(@PathVariable String albumId,
            HttpServletRequest request) {
        albumService.addAlbumToCollection(albumId, request);
        return ResponseEntity.ok("컬렉션에 추가 성공");
    }
}
