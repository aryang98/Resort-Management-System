// Declaring the package where the IUserService interface resides
package com.resort.service;

// Importing necessary classes and entities
import java.util.List;
import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;

// Interface representing User Service
public interface IUserService {

	// Method to fetch all users
	List<UserEntity> fetchAllUsers();

	// Method to save (create/update) a user based on the provided UserRequest
	String saveUser(UserRequest request);

	// Method to delete a user by their ID
	String deleteById(Long id);

	// Method to update user credentials based on the provided UserRequest
	String updateCredientials(UserRequest id);
}
