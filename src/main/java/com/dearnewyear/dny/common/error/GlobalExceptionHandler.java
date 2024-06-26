package com.dearnewyear.dny.common.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(BindException.class)
	public ResponseEntity<ErrorResponse> handleBindException(BindException e) {
		List<String> errorMessages = e.getBindingResult()
			.getAllErrors()
			.stream()
			.map(DefaultMessageSourceResolvable::getDefaultMessage)
			.collect(Collectors.toList());
		log.error("BindException: {}", errorMessages);
		ErrorResponse response = new ErrorResponse(ErrorCode.INVALID_INPUT_VALUE,
			errorMessages.toString());
		return ResponseEntity.status(ErrorCode.INVALID_INPUT_VALUE.getStatus()).body(response);
	}

	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorResponse> handleCustomException(CustomException e) {
		log.error("CustomException: {}", e.getErrorCode());
		ErrorResponse response = new ErrorResponse(e.getErrorCode());
		return ResponseEntity.status(e.getErrorCode().getStatus()).body(response);
	}
}
