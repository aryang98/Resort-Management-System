package com.resort.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;
import com.resort.service.IUserService;

import jakarta.validation.Valid;

import com.resort.dto.GeneralResponseDTO;

@RestController // Annotation to define this class as a REST controller
@RequestMapping("/home") // Mapping for the base URL path
public class UserController {

	@Autowired(required = true) // Autowiring the IUserService bean
	private IUserService userService; // Declaring a variable of IUserService type

	@GetMapping("/users") // Mapping for GET request to "/home/users"
	public ResponseEntity<GeneralResponseDTO> getAllUsers() { // Method to retrieve all users
		List<UserEntity> userList = userService.fetchAllUsers();// Fetching all users from userService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("List of users ", userList);// Creating response DTO
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);// Returning response with HTTP status
	}

	// Mapping for POST request to "/home/register"
	@PostMapping("/register")
	// Method to register a user
	public ResponseEntity<GeneralResponseDTO> saveUser(@Valid @RequestBody UserRequest request) {

		String message = userService.saveUser(request); // Saving user details via userService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Creating response DTO
		// Returning response with HTTP status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

	// Mapping for PUT request to "/home/resetCredientials"
	@PutMapping("/resetCredientials")
	// Method to reset user credentials
	public ResponseEntity<GeneralResponseDTO> updateCredientials(@RequestBody UserRequest request) {
		String message = userService.updateCredientials(request); // Updating user credentials via userService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Creating response DTO
		// Returning response with HTTP status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

	@DeleteMapping("/user/{id}") // Mapping for DELETE request to "/home/user/{id}"
	public ResponseEntity<GeneralResponseDTO> deleteUser(@PathVariable("id") Long id) { // Method to delete user by ID
		String message = userService.deleteById(id); // Deleting user by ID via userService
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message); // Creating response DTO object
		// Returning response with HTTP status
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

}
