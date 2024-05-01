package com.dearnewyear.dny.user.dto.request;

import com.dearnewyear.dny.user.domain.constant.MainBackground;
import com.dearnewyear.dny.user.domain.constant.MainLp;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
@ApiModel(value = "회원가입 요청 모델")
public class SignupRequest {

    @NotBlank
    @ApiModelProperty(value = "유저 이름", required = true)
    private final String userName;

    @NotBlank
    @Email
    @ApiModelProperty(value = "유저 이메일", required = true)
    private final String email;

    @NotBlank
    @ApiModelProperty(value = "유저 메인 배경", required = true)
    private final String mainBackground;

    @NotBlank
    @ApiModelProperty(value = "유저 메인 LP", required = true)
    private final String mainLp;

    @AssertTrue(message = "유효하지 않은 메인 배경입니다.")
    public boolean isValidMainBackground() {
        return MainBackground.isValidMainBackground(mainBackground);
    }

    @AssertTrue(message = "유효하지 않은 메인 LP입니다.")
    public boolean isValidMainLp() {
        return MainLp.isValidMainLp(mainLp);
    }

    public MainBackground getMainBackground() {
        return MainBackground.valueOf(mainBackground);
    }

    public MainLp getMainLp() {
        return MainLp.valueOf(mainLp);
    }
}
