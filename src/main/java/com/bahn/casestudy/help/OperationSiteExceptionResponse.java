package com.bahn.casestudy.help;

import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * To display exceptions in JSON format.
 */
@JsonRootName("Fehler")
public class OperationSiteExceptionResponse implements ExceptionResponse {
	private String message;
	
	public OperationSiteExceptionResponse(Exception e) {
		message = e.getMessage();
	}
		
	@Override
	public String getMessage() {
		return message;
	}
}
