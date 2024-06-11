package com.dearnewyear.dny.music.domain;

import com.dearnewyear.dny.music.domain.constant.Tag;
import com.dearnewyear.dny.music.dto.request.AddMusicRequest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "music")
@Getter
@NoArgsConstructor
public class Music {

	@Id
	private String musicId;

	@Field(name = "music_name")
	@NotNull
	private String musicName;

	@Field(name = "music_artist")
	@NotNull
	private String musicArtist;

	@Indexed(unique = true)
	@Field(name = "youtube_url_id")
	@NotNull
	private String youtubeUrlId;

	@Field(name = "tags")
	private List<Tag> tags = new ArrayList<>();

	@Field(name = "album_ids")
	private List<String> albumIds = new ArrayList<>();

	public Music(AddMusicRequest addMusicRequest) {
		this.musicName = addMusicRequest.getMusicName();
		this.musicArtist = addMusicRequest.getMusicArtist();
		this.youtubeUrlId = addMusicRequest.getYoutubeUrlId();
		this.tags = addMusicRequest.getTags();
	}
}
