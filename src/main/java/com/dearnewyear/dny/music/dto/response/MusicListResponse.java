package com.dearnewyear.dny.music.dto.response;

import com.dearnewyear.dny.music.dto.MusicInfo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MusicListResponse {

	private List<MusicInfo> musicList;
	private String errorMessage;
}
