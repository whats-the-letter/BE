package com.dearnewyear.dny.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

    private final String userName;
    private final String email;
    private final String mainBackground;
    private final String mainLp;
}
