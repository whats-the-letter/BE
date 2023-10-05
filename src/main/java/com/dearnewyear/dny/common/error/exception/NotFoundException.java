package com.dearnewyear.dny.common.error.exception;

import com.dearnewyear.dny.common.error.ErrorCode;

public class NotFoundException extends CustomException {

    public NotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
