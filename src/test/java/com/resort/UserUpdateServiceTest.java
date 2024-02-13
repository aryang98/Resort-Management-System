package com.resort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;
import com.resort.repository.IUserRepo;
import com.resort.service.UserServiceImpl;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserUpdateServiceTest {

	@Mock
	private IUserRepo userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testUpdateCredentials_ShouldReturnSuccessMessage() {
		long userId = 1;
		String newPassword = "Aryan123#";
		String hintQuestion = "allgood";

		UserRequest request = new UserRequest();
		request.setUserId(userId);
		request.setPassword(newPassword);
		request.setHintQuestion(hintQuestion);

		UserEntity mockUser = new UserEntity();
		mockUser.setUserId(userId);
		mockUser.setPassword("aryan@123"); // Setting an old password
		mockUser.setHintQuestion(hintQuestion);

		Mockito.when(userRepo.getReferenceById(anyLong())).thenReturn(mockUser);
		Mockito.when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(new UserEntity());

		String result = userService.updateCredientials(request);

		assertEquals("Password reset successfully:", result);
		assertEquals(newPassword, mockUser.getPassword()); // Verify if the password was updated in the mockUser
	}
}
