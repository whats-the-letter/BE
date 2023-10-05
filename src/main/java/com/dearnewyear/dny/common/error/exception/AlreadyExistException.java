package com.dearnewyear.dny.common.error.exception;

import com.dearnewyear.dny.common.error.ErrorCode;

public class AlreadyExistException extends CustomException {

    public AlreadyExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
