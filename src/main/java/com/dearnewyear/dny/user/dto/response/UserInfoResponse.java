package com.dearnewyear.dny.user.dto.response;

import com.dearnewyear.dny.user.dto.UserInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@ApiModel(value = "로그인 응답 모델")
@AllArgsConstructor
public class UserInfoResponse {

    @ApiModelProperty(value = "유저 정보")
    private UserInfo userInfo;

    @ApiModelProperty(value = "에러 메시지")
    private String errorMessage;
}
