package com.bahn.casestudy.help;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * To display exceptions in JSON format.
 */
@JsonRootName("Fehler")
public class OperationSiteExceptionResponse {
	private String message;
	
	public OperationSiteExceptionResponse(Exception e) {
		message = e.getMessage();
	}
		
	@JsonProperty("Nachricht")
	public String getMessage() {
		return message;
	}
}
