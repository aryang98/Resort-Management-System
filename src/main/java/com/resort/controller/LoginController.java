package com.resort.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.resort.dto.GeneralResponseDTO;
import com.resort.dto.UserRequest;
import com.resort.service.ILoginService;

//This class serves as a REST controller mapped to '/home'
@RestController
@RequestMapping("/home")
public class LoginController {
	@Autowired(required = true) // Autowiring the ILoginService bean
	private ILoginService loginService;// Declaring a variable of ILoginService type

// login controller for user and admin both
	@PostMapping("/login")
	public ResponseEntity<GeneralResponseDTO> loginUser(@RequestBody UserRequest request) {
		/*
		 * Invoking the loginUser method of loginService with the request parameter
		 */
		String message = loginService.loginUser(request);// Saving user details via loginService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO(message, "message");// Creating response DTO
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);// Returning response with HTTP status
	}
}
