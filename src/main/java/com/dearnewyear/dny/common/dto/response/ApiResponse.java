package com.dearnewyear.dny.common.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ApiResponse {

    private final Integer status;
    private final String message;
    private final Object data;
}
