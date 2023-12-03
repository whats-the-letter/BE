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
    private String albumCover;

    @ApiModelProperty(value = "앨범 문구", required = true)
    private String albumPhrases;

    @ApiModelProperty(value = "앨범 배경", required = true)
    private String albumBackground;

    @ApiModelProperty(value = "음악 ID", required = true)
    private Long musicId;

    @ApiModelProperty(value = "받는 사람 ID")
    private Long toUserId;

    @ApiModelProperty(value = "보내는 사람 이름", required = true)
    private String fromName;

    @ApiModelProperty(value = "받는 사람 이름", required = true)
    private String toName;

    @ApiModelProperty(value = "편지 내용", required = true)
    private String letter;
}
