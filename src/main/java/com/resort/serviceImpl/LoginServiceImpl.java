package com.resort.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;
import com.resort.exception.InvalidCredentialsException;
import com.resort.repository.IUserRepo;
import com.resort.service.ILoginService;

//Annotation indicating that this class is a Spring service
@Service
public class LoginServiceImpl implements ILoginService {
	// Autowired annotation to inject IUserRepo bean into this class
	@Autowired(required = true)
	private IUserRepo userRepo;

	// Implementation of the loginUser method from the ILoginService interface
	@Override
	public String loginUser(UserRequest request) {
		// Retrieve user entity from the repository based on the provided userId
		UserEntity user = userRepo.findByUserId(request.getUserId());
		// Check if the user is not found or the password does not match
		if (user == null || !user.getPassword().equals(request.getPassword())) {
			// Throw an exception for invalid credentials
			throw new InvalidCredentialsException("Invalid Credentials");
		}
		// Return a success message if login is successful
		return "Login Successfull";
	}

}