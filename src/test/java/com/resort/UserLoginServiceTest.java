package com.resort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.resort.dto.UserRequest;
import com.resort.entity.UserEntity;
import com.resort.exception.InvalidCredentialsException;
import com.resort.repository.IUserRepo;
import com.resort.service.LoginServiceImpl;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;

public class UserLoginServiceTest {

	@Mock
	private IUserRepo userRepo;

	@InjectMocks
	private LoginServiceImpl ILoginService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testLoginUser_WithValidCredentials_ShouldReturnSuccessMessage() {
		// Mocking userRepo.findByUserId() to return a user with correct credentials
		long userId = 1;
		String password = "aryan@123";
		UserRequest request = new UserRequest();
		request.setUserId(userId);
		request.setPassword(password);

		UserEntity mockUser = new UserEntity();
		mockUser.setUserId(userId);
		mockUser.setPassword(password);

		Mockito.when(userRepo.findByUserId(anyLong())).thenReturn(mockUser);

		String result = ILoginService.loginUser(request);

		assertEquals("Login Successfull", result);
	}

	@Test
	public void testLoginUser_WithInvalidCredentials_ShouldThrowInvalidCredentialsException() {
		// Mocking userRepo.findByUserId() to return null (no user found) or incorrect
		// credentials
		long userId = 1;
		String password = "aryan@123";
		UserRequest request = new UserRequest();
		request.setUserId(userId);
		request.setPassword(password);

		// Mockito.when(userRepo.findByUserId(userId)).thenReturn(null); // No user
		// found

		UserEntity mockUser = new UserEntity();
		mockUser.setUserId(userId);
		mockUser.setPassword("aryan123#");
		Mockito.when(userRepo.findByUserId(userId)).thenReturn(mockUser);
		assertThrows(InvalidCredentialsException.class, () -> ILoginService.loginUser(request));
	}

}
