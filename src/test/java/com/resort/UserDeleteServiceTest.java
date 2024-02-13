package com.resort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.resort.repository.IUserRepo;
import com.resort.service.UserServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

public class UserDeleteServiceTest {

	@Mock
	private IUserRepo userRepo;

	@InjectMocks
	private UserServiceImpl userService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testDeleteById_WhenIdExists_ShouldReturnSuccessMessage() {
		long userId = 1L; // Example user ID

		String result = userService.deleteById(userId);

		assertEquals("User deleted successfully", result);
		verify(userRepo).deleteById(userId);
	}

	// Add more test cases to cover scenarios like deleting a non-existent ID,
	// handling exceptions, etc.
}
