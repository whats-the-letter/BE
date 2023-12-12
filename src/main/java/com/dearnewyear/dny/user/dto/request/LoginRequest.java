package com.dearnewyear.dny.user.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "로그인 요청 모델")
public class LoginRequest {

    @ApiModelProperty(value = "유저 이메일", required = true)
    private String email;
}
