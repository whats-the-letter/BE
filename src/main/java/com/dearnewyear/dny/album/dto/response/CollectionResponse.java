package com.dearnewyear.dny.album.dto.response;

import com.dearnewyear.dny.album.dto.AlbumInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel
@AllArgsConstructor
public class CollectionResponse {

    @ApiModelProperty(value = "앨범 정보 리스트")
    private List<AlbumInfo> albumInfoList;

    @ApiModelProperty(value = "에러 메시지")
    private String errorMessage;
}
