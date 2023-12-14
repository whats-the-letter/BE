package com.dearnewyear.dny.user.dto.request;

import static com.dearnewyear.dny.user.domain.constant.UserPatterns.MAIN_BACKGROUND_PATTERN;
import static com.dearnewyear.dny.user.domain.constant.UserPatterns.MAIN_LP_PATTERN;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.AssertFalse;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "회원가입 요청 모델")
public class SignupRequest {

    @NotEmpty
    @ApiModelProperty(value = "유저 이름", required = true)
    private final String userName;

    @Email
    @ApiModelProperty(value = "유저 이메일", required = true)
    private final String email;

    @NotNull
    @ApiModelProperty(value = "유저 메인 배경", required = true)
    private final String mainBackground;

    @NotNull
    @ApiModelProperty(value = "유저 메인 LP", required = true)
    private final String mainLp;

    @AssertFalse(message = "유저 메인 배경이 유효하지 않습니다.")
    public boolean isValidMainBackground() {
        return MAIN_BACKGROUND_PATTERN.matches(mainBackground);
    }

    @AssertFalse(message = "유저 메인 LP가 유효하지 않습니다.")
    public boolean isValidMainLp() {
        return MAIN_LP_PATTERN.matches(mainLp);
    }
}
