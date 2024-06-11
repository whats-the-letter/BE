package com.dearnewyear.dny.album.controller;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import com.dearnewyear.dny.album.dto.request.AlbumRequest;
import com.dearnewyear.dny.album.dto.response.AlbumResponse;
import com.dearnewyear.dny.album.dto.response.CollectionResponse;
import com.dearnewyear.dny.album.service.AlbumService;

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

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/album")
public class AlbumController {

	private final AlbumService albumService;

	@PostMapping("/send")
	public ResponseEntity<AlbumResponse> sendAlbum(@ModelAttribute @Valid AlbumRequest albumRequest,
		HttpServletRequest request) {
		AlbumInfo albumInfo = albumService.sendAlbum(albumRequest, request);
		return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
	}

	@GetMapping("/view/{albumId}")
	public ResponseEntity<AlbumResponse> viewAlbum(@PathVariable String albumId,
		HttpServletRequest request) {
		AlbumInfo albumInfo = albumService.viewAlbum(albumId, request);
		return ResponseEntity.ok(new AlbumResponse(albumInfo, null));
	}

	@GetMapping("/collection")
	public ResponseEntity<CollectionResponse> viewCollection(HttpServletRequest request) {
		List<AlbumInfo> collection = albumService.viewCollection(request);
		return ResponseEntity.ok(new CollectionResponse(collection, null));
	}

	@PutMapping("/collection/{albumId}")
	public ResponseEntity<String> addCollection(@PathVariable String albumId,
		HttpServletRequest request) {
		albumService.addAlbumToCollection(albumId, request);
		return ResponseEntity.ok("컬렉션에 추가 성공");
	}
}
