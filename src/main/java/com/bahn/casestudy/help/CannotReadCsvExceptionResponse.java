package com.bahn.casestudy.help;

public class CannotReadCsvExceptionResponse implements ExceptionResponse {
	private String message;
	
	public CannotReadCsvExceptionResponse(CannotReadCsvException e) {
		message = e.getMessage();
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
