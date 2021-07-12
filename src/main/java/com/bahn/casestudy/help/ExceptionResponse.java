package com.bahn.casestudy.help;

import com.fasterxml.jackson.annotation.JsonProperty;

public interface ExceptionResponse {
	@JsonProperty("Nachricht")
	public String getMessage();
}
