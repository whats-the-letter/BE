package com.dearnewyear.dny.common.dto.response;

import com.dearnewyear.dny.common.error.ErrorCode;

public record ErrorResponse(Integer status, String message, Object data) {

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage(), null);
    }
}
