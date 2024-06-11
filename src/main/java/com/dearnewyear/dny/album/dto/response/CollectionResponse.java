package com.dearnewyear.dny.album.dto.response;

import com.dearnewyear.dny.album.dto.AlbumInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CollectionResponse {

	private List<AlbumInfo> albumInfoList;
	private String errorMessage;
}
