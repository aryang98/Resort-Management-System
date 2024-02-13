package com.resort.dto;

import org.springframework.stereotype.Component;

/*It instructs Spring to automatically detect and register instances of this class as Spring beans
 *  during the component scanning phase.
 */
@Component
public class GeneralResponseDTO {

	private String message;
	private Object object;

	// Default constructor for the GeneralResponseDTO class
	public GeneralResponseDTO() {
		// Invoking the constructor of the superclass (Object class)
		super();
	}

	/*
	 * Parameterized constructor for the GeneralResponseDTO class, accepting
	 * 'message' of type String and 'object' of type Object
	 */
	public GeneralResponseDTO(String message, Object object) {
		super();
		/*
		 * Assigning the 'message' parameter to the 'message' member variable of this
		 * class
		 */

		this.message = message;
		/*
		 * Assigning the 'object' parameter to the 'object' member variable of this
		 * class
		 */

		this.object = object;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

}
