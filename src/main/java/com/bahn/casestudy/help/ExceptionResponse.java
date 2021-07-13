package com.bahn.casestudy.help;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An interface for the exception responses so they get displayed more
 * beautiful over the web API.
 */
public interface ExceptionResponse {
	/**
	 * The message carried by the exception.
	 * @return The message.
	 */
	@JsonProperty("Nachricht")
	public String getMessage();
}
