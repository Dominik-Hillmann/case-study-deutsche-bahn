package com.bahn.casestudy.info;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.IOException;

/**
 * Gets thrown if API is called that is not yet implemented.
 */
public class ApiNotImplementedException extends IOException {
    /**
     * Constructor.
     * @param message The message carried by the exception.
     */
    public ApiNotImplementedException(String message) {
        super(message);
    }
}
