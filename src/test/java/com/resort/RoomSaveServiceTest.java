package com.resort;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.resort.dto.RoomRequest;
import com.resort.dto.UserType;
import com.resort.entity.UserEntity;
import com.resort.exception.AccessDeniedException;
import com.resort.repository.IRoomRepo;
import com.resort.repository.IUserRepo;
import com.resort.service.RoomServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomSaveServiceTest {

	@Mock
	private IUserRepo userRepo;

	@Mock
	private IRoomRepo roomRepo;

	@InjectMocks
	private RoomServiceImpl roomService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		;
	}

	@Test
	public void testSaveRoom_WithAdminUser_ShouldReturnSuccessMessage() {
		long userId = 1L;
		RoomRequest request = new RoomRequest();
		request.setUserId(userId);

		UserEntity adminUser = new UserEntity();
		adminUser.setUserType(UserType.Admin);

		Mockito.when(userRepo.getReferenceById(userId)).thenReturn(adminUser);

		String result = roomService.saveRoom(request);

		assertEquals("Room added successfully", result);
	}

	@Test
	public void testSaveRoom_WithNonAdminUser_ShouldThrowAccessDeniedException() {
		long userId = 2L;
		RoomRequest request = new RoomRequest();
		request.setUserId(userId);

		UserEntity nonAdminUser = new UserEntity();
		nonAdminUser.setUserType(UserType.User);

		Mockito.when(userRepo.getReferenceById(userId)).thenReturn(nonAdminUser);

		assertThrows(AccessDeniedException.class, () -> roomService.saveRoom(request));
		Mockito.verify(roomRepo, Mockito.never()).save(null);
	}
}
