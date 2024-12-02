package com.andredev.helpdesk.services.exceptions;

public class DataValidationException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	public DataValidationException(String message) {
		super(message);
	}

}
