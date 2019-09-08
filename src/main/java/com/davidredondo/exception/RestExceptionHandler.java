package com.davidredondo.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.davidredondo.entity.response.CustomValidationErrorResponse;

@ControllerAdvice
public class RestExceptionHandler {

	@ExceptionHandler(ValidationException.class)
	protected ResponseEntity<Object> handleValidationException(ValidationException ex) {
		return ResponseEntity.badRequest()
				.body(CustomValidationErrorResponse.build(ex.getInvalidObject(), ex.getMessage()));
	}
}
