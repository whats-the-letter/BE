package com.dearnewyear.dny.common.error.exception;

import com.dearnewyear.dny.common.error.ErrorCode;
import lombok.Getter;

@Getter
public class CustomException extends RuntimeException {

    protected ErrorCode errorCode;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
