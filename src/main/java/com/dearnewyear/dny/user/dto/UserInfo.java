package com.dearnewyear.dny.user.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class UserInfo {

    private final String userId;
    private final String userName;
    private final String email;
    private final String mainBackground;
    private final String mainLp;
    private final List<String> playlist;
}
