package com.dearnewyear.dny.user.dto.request;

import com.dearnewyear.dny.user.domain.constant.MainBackground;
import com.dearnewyear.dny.user.domain.constant.MainLp;
import javax.validation.constraints.AssertTrue;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

    @NotBlank
    private final String userName;

    @NotBlank
    @Email
    private final String email;

    @NotBlank
    private final String mainBackground;

    @NotBlank
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
