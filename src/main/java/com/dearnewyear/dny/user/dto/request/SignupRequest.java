package com.dearnewyear.dny.user.dto.request;

import com.dearnewyear.dny.user.domain.User;
import lombok.Getter;

@Getter
public class SignupRequest {

    private final String email;
    private final String userName;
    private final String mainBackground;
    private final String mainLp;

    public SignupRequest(String email, String userName, String mainBackground, String mainLp) {
        this.email = email;
        this.userName = userName;
        this.mainBackground = mainBackground;
        this.mainLp = mainLp;
    }
}
