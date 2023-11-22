package com.dearnewyear.dny.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "회원가입 요청 모델")
public class SignupRequest {

    @ApiModelProperty(value = "유저 이름", required = true)
    private final String userName;

    @ApiModelProperty(value = "유저 이메일", required = true)
    private final String email;

    @ApiModelProperty(value = "유저 메인 배경", required = true)
    private final String mainBackground;

    @ApiModelProperty(value = "유저 메인 LP", required = true)
    private final String mainLp;
}
