package com.resort.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.dto.RoomRequest;
import com.resort.entity.Room;
import com.resort.entity.UserEntity;
import com.resort.exception.AccessDeniedException;
import com.resort.exception.InvalidCredentialsException;
import com.resort.repository.IRoomRepo;
import com.resort.repository.IUserRepo;

//Annotation indicating that this class is a Spring service
@Service
public class RoomServiceImpl implements IRoomService {
	// Autowired annotation to inject IRoomRepo bean into this class
	@Autowired(required = true)
	private IRoomRepo roomRepo;
	// Autowired annotation to inject IUserRepo bean into this class
	@Autowired(required = true)
	private IUserRepo userRepo;

	public RoomServiceImpl() {
		super();
	}

	/*
	 * constructor that receives an instance of IRoomRepo,IUserRepo and sets it to
	 * the roomRepo,userRepo field.
	 */
	public RoomServiceImpl(IUserRepo userRepo, IRoomRepo roomRepo) {
		this.userRepo = userRepo;
		this.roomRepo = roomRepo;
	}

	@Override
	public List<Room> fetchAllRooms() {
		return roomRepo.findAll();
	}

	/*
	 * Annotation indicating that this method overrides a method in a interface
	 */
	@Override
	public String saveRoom(RoomRequest request) {
		// Retrieve a user entity reference based on the provided userId
		UserEntity user = userRepo.getReferenceById(request.getUserId());

		// Check if the user type is Admin
		if (user.getUserType().toString().equals("Admin")) {
			/*
			 * If the user is an Admin, create a new Room object using the provided
			 * RoomRequest
			 */
			Room room = new Room(request);

			// Save the created Room object to the room repository
			roomRepo.save(room);

			// Return a success message indicating that the room has been added successfully
			return "Room added successfully";
		} else {
			/*
			 * If the user is not an Admin, throw an AccessDeniedException with a specific
			 * message
			 */
			throw new AccessDeniedException("Access denied, User has not rights to save room.");
		}
	}

	/*
	 * Method signature indicating that this method overrides a method in the
	 * interface
	 */
	@Override
	public String deleteRoomById(RoomRequest request) {
		// Retrieve a UserEntity using the userId from the request
		UserEntity user = userRepo.getReferenceById(request.getUserId());

		// Check if the user type is 'Admin'
		if (user.getUserType().toString().equals("Admin")) {
			// If user is an admin, delete the room with the specified roomId
			roomRepo.deleteById(request.getRoomId());
			// Return a success message
			return "Room deleted successfully";
		} else {
			// If the user is not an admin, throw an AccessDeniedException
			throw new AccessDeniedException("Access denied, User has no rights to delete room.");
		}
	}

	/*
	 * Annotation indicating that this method overrides a method in a interface
	 */
	@Override
	public String updateRoom(RoomRequest request) {
		/*
		 * Retrieve a user entity based on the provided userId using a method in the
		 * userRepo
		 */
		UserEntity user = userRepo.getReferenceById(request.getUserId());

		// Initialize a message string for indicating the result of the room update
		String message = "Room Updated";

		// Check if the user's userType is "Admin"
		if (user.getUserType().toString().equals("Admin")) {
			/*
			 * Retrieve a Room entity based on the provided roomId using a method in the
			 * roomRepo
			 */
			Room room = roomRepo.getReferenceById(request.getRoomId());

			// Update various properties of the room entity with values from the request
			room.setRoomType(request.getRoomType());
			room.setCapacity(request.getCapacity());
			room.setAmenities(request.getAmenities());
			room.setPrice(request.getPrice());

			// Save the updated room entity back to the repository
			roomRepo.save(room);

			// Return a success message
			return message;
		} else {
			/*
			 * Throw an exception indicating that the user does not have the right to update
			 * the room
			 */
			throw new AccessDeniedException("Access denied, User has not rights to update room.");
		}
	}

	// Method declaration, implementing the fetchByFilter method from the interface
	@Override
	public List<Room> fetchByFilter(RoomRequest request) {
		// Retrieve the minimum price from the RoomRequest object
		Double min = request.getMinPrice();
		if (isRangeValid(request)) {
			// Check if the roomType in the request is not null
			if (request.getRoomType() != null) {
				// Declare a List variable to store rooms
				List<Room> room;

				// Check if the minimum price is greater than 0.0
				if (min > 0.0) {
					// Retrieve rooms from the repository within the specified price range
					room = roomRepo.findByPriceBetween(request.getMinPrice(), request.getMaxPrice());
				} else {
					// If minimum price is not specified, retrieve all rooms from the repository
					room = roomRepo.findAll();
				}

				/*
				 * Filter rooms based on availability and room type, and collect them into a new
				 * List
				 */
				List<Room> filteredRoom = room.stream()
						.filter(roomType -> roomType.isAvailability()
								&& roomType.getRoomType().toString().equals(request.getRoomType().toString()))
						.collect(Collectors.toList());

				// Return the filtered list of rooms
				return filteredRoom;
			} else if (min > 0.0) {
				/*
				 * If roomType is null but minimum price is specified, retrieve rooms within the
				 * specified price range
				 */
				List<Room> room = roomRepo.findByPriceBetween(request.getMinPrice(), request.getMaxPrice());

				// Filter rooms based on availability and collect them into a new List
				List<Room> filteredRoom = room.stream().filter(Room::isAvailability).collect(Collectors.toList());

				// Return the filtered list of rooms
				return filteredRoom;
			} else {
				/*
				 * If neither roomType nor minimum price is specified, retrieve all rooms from
				 * the repository
				 */
				return roomRepo.findAll();
			}
		} else {
			throw new InvalidCredentialsException("Provide Valid Range.");
		}
	}

	// added condition for check valid price range given by user
	private boolean isRangeValid(RoomRequest request) {
		Double min = request.getMinPrice();
		Double max = request.getMaxPrice();

		return min <= max && min > 0.0;
	}

}
