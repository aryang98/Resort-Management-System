package com.resort.exception;

//Custom exception class for Access Denied scenarios
public class AccessDeniedException extends RuntimeException {

	// Generated serialVersionUID to maintain serialization compatibility
	private static final long serialVersionUID = 1L;

	// Constructor to set the exception message
	public AccessDeniedException(String message) {
		super(message);
	}
}
