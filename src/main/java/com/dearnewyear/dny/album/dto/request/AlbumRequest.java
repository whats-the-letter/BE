package com.dearnewyear.dny.album.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "앨범 보내기 요청 모델")
public class AlbumRequest {

    @ApiModelProperty(value = "앨범 커버", required = true)
    private final String albumCover;

    @ApiModelProperty(value = "앨범 문구", required = true)
    private final String albumPhrases;

    @ApiModelProperty(value = "앨범 배경", required = true)
    private final String albumBackground;

    @ApiModelProperty(value = "음악 ID", required = true)
    private final Long musicId;

    @ApiModelProperty(value = "받는 사람 ID")
    private final Long toUserId;

    @ApiModelProperty(value = "보내는 사람 이름", required = true)
    private final String fromName;

    @ApiModelProperty(value = "받는 사람 이름", required = true)
    private final String toName;

    @ApiModelProperty(value = "편지 내용", required = true)
    private final String letter;
}
