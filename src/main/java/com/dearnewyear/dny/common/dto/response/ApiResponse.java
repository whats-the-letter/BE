package com.dearnewyear.dny.common.dto.response;

public record ApiResponse(Integer status, String message, Object data) {

    public static ApiResponse of(Integer status, String message, Object data) {
        return new ApiResponse(status, message, data);
    }
}
