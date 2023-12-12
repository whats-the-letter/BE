package com.dearnewyear.dny.music.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "음악 추가 요청 모델")
public class AddMusicRequest {

    @ApiModelProperty(value = "음악 이름", required = true)
    private String musicName;

    @ApiModelProperty(value = "아티스트", required = true)
    private String musicArtist;

    @ApiModelProperty(value = "youtube url id", required = true)
    private String youtubeUrlId;

    @ApiModelProperty(value = "카테고리", required = true)
    private String category;
}
