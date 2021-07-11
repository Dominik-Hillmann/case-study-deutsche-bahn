package com.bahn.casestudy.operationsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OperationSiteExceptionHelper {
	
	@ExceptionHandler(value = {OperationSiteNotFoundException.class})
	public ResponseEntity<OperationSiteExceptionResponse> handleOperationSiteNotFoundException(OperationSiteNotFoundException e) {
		return new ResponseEntity<OperationSiteExceptionResponse>(
			new OperationSiteExceptionResponse(e),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	
}
