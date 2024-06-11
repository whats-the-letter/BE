package com.dearnewyear.dny.music.dto.request;

import com.dearnewyear.dny.music.domain.constant.Tag;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AddMusicRequest {

	@NotBlank
	private final String musicName;

	@NotBlank
	private final String musicArtist;

	@NotBlank
	private final String youtubeUrlId;

	@NotEmpty
	private final List<String> tags;

	@AssertTrue(message = "유효하지 않은 태그입니다.")
	public boolean isValidTags() {
		return tags.stream().allMatch(Tag::isValidTag);
	}

	public List<Tag> getTags() {
		return tags.stream()
			.map(Tag::valueOf)
			.collect(Collectors.toList());
	}
}
