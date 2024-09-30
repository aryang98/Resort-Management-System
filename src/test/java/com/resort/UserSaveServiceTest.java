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
import com.resort.serviceImpl.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class UserSaveServiceTest {

	@Mock
	private IUserRepo userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testSaveUser_WithValidData_ShouldReturnSuccessMessage() {
		UserRequest request = new UserRequest();
		request.setEmail("aryank@gmail.com");
		request.setPhoneNo("9234567890");
		request.setFirstName("Aryan");
		request.setLastName("Kumar");
		request.setPassword("Aryan@123");
		request.setHintQuestion("allgood?");

		Mockito.when(userRepo.save(Mockito.any(UserEntity.class))).thenReturn(new UserEntity());

		String result = userService.saveUser(request);

		assertEquals("Registered successfully:", result);
	}
}
