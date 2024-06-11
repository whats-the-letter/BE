package com.dearnewyear.dny.album.dto;

import com.dearnewyear.dny.album.domain.Album;
import com.dearnewyear.dny.music.domain.Music;
import com.dearnewyear.dny.music.domain.constant.Tag;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class AlbumInfo {

	private final String albumId;
	private final String albumCover;
	private final String albumPhrases;
	private final String albumBackground;
	private final String musicName;
	private final String musicArtist;
	private final String youtubeUrlId;
	private final List<Tag> tags;
	private final String fromName;
	private final String toName;
	private final String letter;
	private final String frontImage;
	private final String backImage;

	public AlbumInfo(Album album, Music music) {
		this.albumId = album.getAlbumId();
		this.albumCover = album.getAlbumCover().name();
		this.albumPhrases = album.getAlbumPhrases().name();
		this.albumBackground = album.getAlbumBackground().name();
		this.musicName = music.getMusicName();
		this.musicArtist = music.getMusicArtist();
		this.youtubeUrlId = music.getYoutubeUrlId();
		this.tags = music.getTags();
		this.fromName = album.getFromName();
		this.toName = album.getToName();
		this.letter = album.getLetter();
		this.frontImage = album.getFrontImage();
		this.backImage = album.getBackImage();
	}
}
