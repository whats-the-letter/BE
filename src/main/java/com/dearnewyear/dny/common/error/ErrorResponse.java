package com.dearnewyear.dny.common.error;

import lombok.Getter;

@Getter
public class ErrorResponse {

    private final int status;
    private final String code;
    private final String message;

    public ErrorResponse(ErrorCode errorCode) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = errorCode.getMessage();
    }

    public ErrorResponse(ErrorCode errorCode, String message) {
        this.status = errorCode.getStatus();
        this.code = errorCode.getCode();
        this.message = message;
    }

    @Override
    public String toString() {
        return "{\"status\":" + status + ",\"code\":\"" + code + "\",\"message\":\"" + message
                + "\"}";
    }
}
