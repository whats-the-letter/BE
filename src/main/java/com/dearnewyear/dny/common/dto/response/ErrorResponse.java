package com.dearnewyear.dny.common.dto.response;

import com.dearnewyear.dny.common.error.ErrorCode;
import lombok.Getter;

@Getter
public final class ErrorResponse extends ApiResponse {

    public ErrorResponse(Integer status, String message) {
        super(status, message, null);
    }

    public static ErrorResponse of(ErrorCode errorCode) {
        return new ErrorResponse(errorCode.getStatus(), errorCode.getMessage());
    }
}
