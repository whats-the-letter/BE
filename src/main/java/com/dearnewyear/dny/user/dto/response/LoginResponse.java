package com.dearnewyear.dny.user.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class LoginResponse {

    private final String userName;
    private final String email;
    private final String mainBackground;
    private final String mainLp;
}
