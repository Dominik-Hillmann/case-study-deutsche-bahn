package com.bahn.casestudy.operationsite;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.bahn.casestudy.help.CannotReadCsvException;
import com.bahn.casestudy.help.CannotReadCsvExceptionResponse;
import com.bahn.casestudy.help.OperationSiteExceptionResponse;
import com.bahn.casestudy.help.OperationSiteNotFoundException;

@RestControllerAdvice
public class OperationSiteExceptionHelper {
	
	@ExceptionHandler(value = {OperationSiteNotFoundException.class})
	public ResponseEntity<OperationSiteExceptionResponse> handleOperationSiteNotFoundException(OperationSiteNotFoundException e) {
		return new ResponseEntity<OperationSiteExceptionResponse>(
			new OperationSiteExceptionResponse(e),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	
	@ExceptionHandler(value = {CannotReadCsvException.class})
	public ResponseEntity<CannotReadCsvExceptionResponse> handleCannotReadCsvException(CannotReadCsvException e) {
		return new ResponseEntity<CannotReadCsvExceptionResponse>(
			new CannotReadCsvExceptionResponse(e),
			HttpStatus.INTERNAL_SERVER_ERROR
		);
	}
	
}
