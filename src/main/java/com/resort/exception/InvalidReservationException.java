package com.resort.exception;

//Custom exception class for handling invalid reservation scenarios
public class InvalidReservationException extends RuntimeException {

	// Generated serialVersionUID to maintain serialization compatibility
	private static final long serialVersionUID = 1L;

	// Constructor taking a message to be passed to the superclass
	public InvalidReservationException(String message) {
		super(message);
	}

}
