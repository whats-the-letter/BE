package com.dearnewyear.dny.user.dto.response;

import com.dearnewyear.dny.user.dto.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserInfoResponse {

    private UserInfo userInfo;
    private String errorMessage;
}
