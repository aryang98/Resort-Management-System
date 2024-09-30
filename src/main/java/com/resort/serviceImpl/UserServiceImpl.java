package com.resort.serviceImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;
import com.resort.exception.InvalidCredentialsException;
import com.resort.repository.IUserRepo;
import com.resort.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	// Annotation to inject an instance of IUserRepo into this class
	@Autowired(required = true)
	private IUserRepo userRepo;// Declaration of a private IUserRepo variable named userRepo

	@Override
	public List<UserEntity> fetchAllUsers() {
		return userRepo.findAll();
	}

	// Indicates that the method overrides a method in a superclass or interface
	@Override
	/*
	 * Method declaration with a parameter of type UserRequest and returning a
	 * String
	 */
	public String saveUser(UserRequest request) {
		String message;
		// Creating a new UserEntity object using the UserRequest object
		UserEntity userEntity = new UserEntity(request);
		try {
			userRepo.save(userEntity);
			message = "Registered successfully:";
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			message ="This email or phone already exists.";

		}
		return message;
	}

	@Override
	public String deleteById(Long id) { // Delete user by ID and return success message
		userRepo.deleteById(id);
		// Return a success message indicating that the user has been deleted.
		return "User deleted successfully";
	}

	/*
	 * This annotation indicates that the following method is intended to override a
	 * method in a superclass or interface.
	 */
	@Override
	public String updateCredientials(UserRequest request) {
		/*
		 * Retrieve a reference to the UserEntity using the user repository and the
		 * provided user ID.
		 */
		UserEntity user = userRepo.getReferenceById(request.getUserId());
		/*
		 * Check if the user reference is not null and if the hint question in the
		 * request matches the hint question stored in the user entity.
		 */
		if (user != null && request.getHintQuestion().equals(user.getHintQuestion())) {
			/*
			 * If the conditions are met, reset the user's password with the new password
			 * from the request.
			 */
			user.setPassword(request.getPassword());
			// Save the reset user entity in the repository.
			userRepo.save(user);
			// Return a success message indicating that the password has been reset.
			return "Password reset successfully:";
		} else {
			/*
			 * If the conditions are not met (either user is null or hint question doesn't
			 * match), throw an exception indicating invalid credentials.
			 */
			throw new InvalidCredentialsException("Invalid hint question.");

		}

	}

}