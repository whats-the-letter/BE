package com.dearnewyear.dny.common.error.exception;

import com.dearnewyear.dny.common.error.ErrorCode;

public class PermissionDeniedException extends CustomException {

    public PermissionDeniedException(ErrorCode errorCode) {
        super(errorCode);
    }
}
