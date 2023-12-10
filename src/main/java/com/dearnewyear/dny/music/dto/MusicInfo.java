package com.dearnewyear.dny.music.dto;

import com.dearnewyear.dny.music.domain.Music;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value = "음악 정보 모델")
public class MusicInfo {

    @ApiModelProperty(value = "음악 ID")
    private final Long musicId;

    @ApiModelProperty(value = "음악 이름")
    private final String musicName;

    @ApiModelProperty(value = "아티스트")
    private final String musicArtist;

    @ApiModelProperty(value = "유튜브 URL ID")
    private final String youtubeUrlId;

    @ApiModelProperty(value = "카테고리")
    private final String category;

    public MusicInfo(Music music) {
        this.musicId = music.getMusicId();
        this.musicName = music.getMusicName();
        this.musicArtist = music.getMusicArtist();
        this.youtubeUrlId = music.getYoutubeUrlId();
        this.category = String.valueOf(music.getCategory());
    }
}
