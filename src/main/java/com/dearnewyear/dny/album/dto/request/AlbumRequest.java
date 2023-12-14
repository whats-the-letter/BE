package com.dearnewyear.dny.album.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "앨범 보내기 요청 모델")
public class AlbumRequest {

    @NotNull
    @ApiModelProperty(value = "앨범 커버", required = true)
    private final String albumCover;

    @NotNull
    @ApiModelProperty(value = "앨범 문구", required = true)
    private final String albumPhrases;

    @NotNull
    @ApiModelProperty(value = "앨범 배경", required = true)
    private final String albumBackground;

    @NotNull
    @Positive
    @ApiModelProperty(value = "음악 ID", required = true)
    private final Long musicId;

    @Positive
    @ApiModelProperty(value = "받는 사람 ID")
    private final Long toUserId;

    @NotEmpty
    @ApiModelProperty(value = "보내는 사람 이름", required = true)
    private final String fromName;

    @NotEmpty
    @ApiModelProperty(value = "받는 사람 이름", required = true)
    private final String toName;

    @NotEmpty
    @ApiModelProperty(value = "편지 내용", required = true)
    private final String letter;
}
