package com.bahn.casestudy.info;

import com.bahn.casestudy.help.ExceptionResponse;

/**
 * Wrapper to properly display this response.
 */
public class ApiNotImplementedExceptionResponse implements ExceptionResponse {
    /** Message carried by the exception. */
    private String message;

    /**
     * Constructor.
     * @param e The exception to be displayed.
     */
    public ApiNotImplementedExceptionResponse(ApiNotImplementedException e) {
        message = e.getMessage();
    }

    @Override
    public String getMessage() {
        return message;
    }
}
