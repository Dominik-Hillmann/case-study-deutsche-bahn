package com.bahn.casestudy.help;

import java.io.IOException;

public class CannotReadCsvException extends IOException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4696787236179386309L;

	public CannotReadCsvException(String message) {
		super(message);
	}
}
