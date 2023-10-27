package com.dearnewyear.dny.common.dto.response;

import com.dearnewyear.dny.common.error.ErrorCode;

public final class ErrorResponse {

    private final Integer status;
    private final String message;

    ErrorResponse(Integer status, String message) {
        this.status = status;
        this.message = message;
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
    }
}
