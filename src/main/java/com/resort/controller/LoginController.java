package com.resort.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

// This class serves as a REST controller for handling login requests
@RestController
@RequestMapping("/home")
public class LoginController {

    // Logger instance for logging
    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired(required = true)
    private ILoginService loginService;

    // Endpoint for user and admin login
    @PostMapping("/login")
    public ResponseEntity<GeneralResponseDTO> loginUser(@RequestBody UserRequest request) {
        try {
            // Log the request payload
            logger.info("Received login request: {}", request);
            
            // Invoke the loginUser method of loginService with the request parameter
            String message = loginService.loginUser(request);
            
            // Log the response message
            logger.info("Login response: {}", message);
            
            // Create response DTO
            GeneralResponseDTO responseDTO = new GeneralResponseDTO(message, "message");
            
            // Return response with HTTP status
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } catch (Exception e) {
            // Log any errors that occur
            logger.error("Error occurred during login: {}", e.getMessage(), e);
            
            // Return error response with HTTP status
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}