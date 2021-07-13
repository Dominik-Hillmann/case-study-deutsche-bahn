package com.bahn.casestudy.help;

/**
 * A wrapper for the {@link CannotReadCsvException} so it gets expressed 
 * beautifully over the web API.
 */
public class CannotReadCsvExceptionResponse implements ExceptionResponse {
	/** The exception's message. */
	private String message;
	
	
	/**
	 * The constructor.
	 * @param e The exception that gets wrapped.
	 */
	public CannotReadCsvExceptionResponse(CannotReadCsvException e) {
		message = e.getMessage();
	}
	
	@Override
	public String getMessage() {
		return message;
	}

}
