package com.resort.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.resort.dto.GeneralResponseDTO;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

	// Exception handler for InvalidCredentialsException
	@ExceptionHandler(InvalidCredentialsException.class)
	public ResponseEntity<GeneralResponseDTO> handleInvalidCredentialsException(InvalidCredentialsException ex) {
		/*
		 * Create a response with the exception message and an empty string for
		 * additional information
		 */
		GeneralResponseDTO responseDTO = new GeneralResponseDTO(ex.getMessage(), "");
		// Return ResponseEntity with UNAUTHORIZED status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.UNAUTHORIZED);
	}

	// Exception handler for InvalidReservationException
	@ExceptionHandler(InvalidReservationException.class)
	public ResponseEntity<GeneralResponseDTO> handleInvalidReservationException(InvalidReservationException ex) {
		/*
		 * Create a response with the exception message and an empty string for
		 * additional information
		 */
		GeneralResponseDTO responseDTO = new GeneralResponseDTO(ex.getMessage(), "");
		// Return ResponseEntity with BAD_REQUEST status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

	// Exception handler for AccessDeniedException
	@ExceptionHandler(AccessDeniedException.class)
	public ResponseEntity<GeneralResponseDTO> handleAccessDeniedException(AccessDeniedException ex) {
		/*
		 * Create a response with the exception message and an empty string for
		 * additional information
		 */
		GeneralResponseDTO responseDTO = new GeneralResponseDTO(ex.getMessage(), "");
		// Return ResponseEntity with FORBIDDEN status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.FORBIDDEN);
	}

	// This method handles exceptions of type MethodArgumentNotValidException
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleMethodArgumentNotValidException(
			MethodArgumentNotValidException ex) {
		// Create a Map to store field names and corresponding error messages
		Map<String, String> resp = new HashMap<>();
		// Iterate through all errors in the binding result
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			// Get the field name associated with the error
			String fieldName = ((FieldError) error).getField();
			// Get the default error message
			String message = error.getDefaultMessage();
			// Put the field name and message in the response Map
			resp.put(fieldName, message);
		});
		// Return a ResponseEntity with the response Map and HTTP status BAD_REQUEST
		return new ResponseEntity<Map<String, String>>(resp, HttpStatus.BAD_REQUEST);
	}

	// This annotation declares that this method handles exceptions of type
	// EntityNotFoundException.
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<GeneralResponseDTO> handleEntityNotFoundException(EntityNotFoundException ex) {
		// Create a GeneralResponseDTO with the error message from the exception and an
		// empty additional message.
		String errorMessage = "Id does not exists:";
		GeneralResponseDTO responseDTO = new GeneralResponseDTO(errorMessage, "");

		// Return a new ResponseEntity containing the responseDTO and an HTTP status of
		// INTERNAL_SERVER_ERROR.
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<GeneralResponseDTO> handleHttpMessageNotReadableException(
			HttpMessageNotReadableException ex) {
		// Customize the error message
		String errorMessage ="Use the appropriate data type value.";

		// Log the original exception for debugging

		GeneralResponseDTO responseDTO = new GeneralResponseDTO(errorMessage, "");

		// Return a customized error response
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.BAD_REQUEST);
	}

}
