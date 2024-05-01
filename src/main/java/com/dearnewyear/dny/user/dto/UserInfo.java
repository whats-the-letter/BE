package com.dearnewyear.dny.user.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
@ApiModel(value = "유저 정보 모델")
public class UserInfo {

    @ApiModelProperty(value = "유저 ID")
    private final String userId;

    @ApiModelProperty(value = "유저 닉네임")
    private final String userName;

    @ApiModelProperty(value = "유저 이메일, 타 유저 정보 조회 시에는 null")
    private final String email;

    @ApiModelProperty(value = "유저 메인 배경")
    private final String mainBackground;

    @ApiModelProperty(value = "유저 메인 LP")
    private final String mainLp;

    @ApiModelProperty(value = "playlist")
    private final List<String> playlist;
}
