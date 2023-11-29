package com.dearnewyear.dny.album.dto.response;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel(value = "앨범 조회 응답 모델")
@AllArgsConstructor
public class AlbumResponse {

    @ApiModelProperty(value = "앨범 정보")
    private AlbumInfo albumInfo;

    @ApiModelProperty(value = "에러 메시지")
    private String errorMessage;
}
