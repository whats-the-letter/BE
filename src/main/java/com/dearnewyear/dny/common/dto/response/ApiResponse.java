package com.dearnewyear.dny.common.dto.response;

import lombok.Builder;

@Builder
public class ApiResponse {

    private final Integer status;
    private final String message;
    private final Object data;


    ApiResponse(Integer status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
