package com.dearnewyear.dny.music.dto.response;

import com.dearnewyear.dny.music.dto.MusicInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel(value = "음악 목록 응답 모델")
@AllArgsConstructor
public class MusicListResponse {

    @ApiModelProperty(value = "음악 목록")
    private List<MusicInfo> musicList;

    @ApiModelProperty(value = "에러 메시지")
    private String errorMessage;
}
