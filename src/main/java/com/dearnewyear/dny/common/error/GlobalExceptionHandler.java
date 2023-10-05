package com.dearnewyear.dny.common.error;

import com.dearnewyear.dny.common.dto.response.ErrorResponse;
import com.dearnewyear.dny.common.error.exception.AuthErrorException;
import com.dearnewyear.dny.common.error.exception.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e, ErrorCode errorCode) {
        log.warn(e.getMessage(), e);
        final ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }

    @ExceptionHandler(AuthErrorException.class)
    public ResponseEntity<ErrorResponse> handleAuthErrorException(AuthErrorException e, ErrorCode errorCode) {
        log.warn(e.getMessage(), e);
        final ErrorResponse errorResponse = ErrorResponse.of(errorCode);
        return ResponseEntity.status(errorCode.getStatus()).body(errorResponse);
    }
}
