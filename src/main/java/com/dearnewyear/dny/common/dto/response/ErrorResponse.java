package com.dearnewyear.dny.common.dto.response;

public record ErrorResponse(Integer status, String message, Object data) {

    public static ErrorResponse of(Integer status, String message, Object data) {
        return new ErrorResponse(status, message, data);
    }
}
