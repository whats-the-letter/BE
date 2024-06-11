package com.dearnewyear.dny.music.dto;

import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.domain.constant.Tag;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MusicInfo {

	private final String musicId;
	private final String musicName;
	private final String musicArtist;
	private final String youtubeUrlId;
	private List<Tag> tags;

	public MusicInfo(Music music) {
		this.musicId = music.getMusicId();
		this.musicName = music.getMusicName();
		this.musicArtist = music.getMusicArtist();
		this.youtubeUrlId = music.getYoutubeUrlId();
		this.tags = music.getTags();
	}
}
