package com.resort.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.resort.dto.ReservationRequest;
import com.resort.entity.Reservation;
import com.resort.entity.Room;
import com.resort.entity.UserEntity;
import com.resort.exception.AccessDeniedException;
import com.resort.exception.InvalidCredentialsException;
import com.resort.exception.InvalidReservationException;
import com.resort.repository.IReservationRepo;
import com.resort.repository.IRoomRepo;
import com.resort.repository.IUserRepo;

//Annotation indicating that this class is a service component managed by Spring
@Service
public class ReservationServiceImpl implements IReservationService {
	/*
	 * Autowired annotation to inject an instance of IReservationRepo (interface)
	 * into this field
	 */
	@Autowired(required = true)
	private IReservationRepo reservationRepo;
	/*
	 * Autowired annotation to inject an instance of IRoomRepo (interface) into this
	 * field
	 */
	@Autowired(required = true)
	private IRoomRepo roomRepo;
	/*
	 * Autowired annotation to inject an instance of IUserRepo (interface) into this
	 * field
	 */
	@Autowired(required = true)
	private IUserRepo userRepo;

	/*
	 * Creating a constructor for the ReservationServiceImpl class that takes an
	 * IReservationRepo instance as a parameter.
	 */
	public ReservationServiceImpl(IReservationRepo reservationRepo) {
		/*
		 * Assigning the passed IReservationRepo parameter to the reservationRepo field
		 * of the ReservationServiceImpl class using 'this' keyword.
		 */
		this.reservationRepo = reservationRepo;
	}

	@Override
	// Overrides the method to fetch all reservations based on the given request
	public List<Reservation> fetchAllReservation(ReservationRequest request) {
		// Fetches the user information using the user ID from the request
		UserEntity user = userRepo.getReferenceById(request.getUser().getUserId());
		// Initializes an empty list to hold reservations
		List<Reservation> reser = new ArrayList<>();
		// Checks if the userType is "Admin"
		if (user.getUserType().toString().equals("Admin")) {
			// Returns all reservations if the userType is an admin
			return reservationRepo.findAll();
		} else {
			// Returns an empty list if the user is not an admin
			return reser;
		}
	}

	// Annotation indicating that this method overrides a method from a interface
	@Override
	public Optional<Reservation> getReservationById(Long id) {
		/*
		 * Call the findById method on the reservationRepo to retrieve a Reservation by
		 * its ID, The result is wrapped in an Optional to handle the case when the
		 * reservation is not found
		 */
		return reservationRepo.findById(id);
	}

	/*
	 * Method overriding the updateStatus method from a specific interface
	 */
	@Override
	public String updateStatus(ReservationRequest request) {
		// Retrieve a UserEntity reference based on the provided userId
		UserEntity user = userRepo.getReferenceById(request.getUser().getUserId());

		// Declare a variable to store the update status message
		String message;

		// Check if the user's userType is Admin
		if (user.getUserType().toString().equals("Admin")) {
			// Retrieve a Reservation reference based on the provided reservationId
			Reservation reservation = reservationRepo.getReferenceById(request.getReservationId());

			// Check if the requested status is "ACCE" (accepted)
			if (request.getStatus().toString() == "ACCE") {
				// Set the status of the reservation to the requested status
				reservation.setStatus(request.getStatus());
			} else {
				// Set the status of the reservation to the requested status
				reservation.setStatus(request.getStatus());

				// Retrieve a Room reference based on the reservation's roomId
				Room room = roomRepo.getReferenceById(reservation.getRoomId());

				// Set the availability of the room to true
				room.setAvailability(true);

				// Save the updated room information to the repository
				roomRepo.save(room);
			}

			// Save the updated reservation information to the repository
			reservationRepo.save(reservation);

			// Set the message indicating a successful status update
			message = "Reservation Status Updated to " + request.getStatus();

			// Return the update status message
			return message;
		} else {
			// Throw an AccessDeniedException if the user is not an Admin
			throw new AccessDeniedException("Access denied, User does not have rights to update status.");
		}
	}

	@Override
	public String makeReservation(ReservationRequest request) {
		boolean bool = userRepo.existsById(request.getUser().getUserId());
		// Declare a variable to store a message
		String message;
		// Retrieve all rooms from the room repository
		List<Room> room = roomRepo.findAll();
		// Filter the rooms based on availability and room type specified in the request
		Room filteredRoom = room.stream()
				.filter(roomType -> roomType.isAvailability() == true
						&& roomType.getRoomType().toString().equals(request.getRoomType().toString()))
				.findFirst().orElse(null);
		// Check if no available rooms match the criteria
		if (filteredRoom == null) {
			// Throw an exception indicating that the room is not available
			throw new InvalidReservationException("Room is not available");

		} else {
			// Additional check for valid check-in and check-out dates
			if (request.getCheckOutDate().before(request.getCheckInDate())) {
				// Throw an exception for invalid check-in or check-out dates
				throw new InvalidReservationException("Invalid check-in or check-out dates");
			} else {
				if (bool) {
					// Create a new Reservation object based on the request
					Reservation reservation = new Reservation(request);
					/*
					 * Set the room ID for the reservation based on the first available room in the
					 * filtered list
					 */
					reservation.setRoomId(filteredRoom.getRoomId());
					// Mark the first available room as unavailable (already booked)
					filteredRoom.setAvailability(false);
					// Save the updated room availability status to the repository
					roomRepo.save(filteredRoom);
					// Save the reservation details to the reservation repository
					reservationRepo.save(reservation);
					// Set a succ"ess message indicating the reservation was successful
					message = "Reservation successful";
					return message;
				} else {
					throw new InvalidCredentialsException("user not available");
				}
			}
		}
	}

	@Override
	public String makePayment(ReservationRequest request) {
		// Retrieves a Reservation object by its ID from the reservation repository
		Reservation reservation = reservationRepo.getReferenceById(request.getReservationId());
		/*
		 * / Sets the payment details from the request into the retrieved Reservation
		 * object
		 */
		reservation.setPayment(request.getPayment());
		// Saves the updated Reservation object in the repository/database
		reservationRepo.save(reservation);
		// Returns a success message indicating that the payment was successful
		return "Payment Successfull";
	}

	@Override
	// Updates the reservation based on the request
	public String updateReservation(ReservationRequest request) {
		String message = "";
		// Fetching the reservation by ID from the repository based on the request ID
		Reservation reservation = reservationRepo.getReferenceById(request.getReservationId());

		/*
		 * Checking if the user ID from the request matches the user ID associated with
		 * the reservation
		 */
		if (request.getUser().getUserId() == reservation.getUser().getUserId()) {
			// If user IDs match, update check-in and check-out dates for the reservation
			reservation.setCheckInDate(request.getCheckInDate());
			reservation.setCheckOutDate(request.getCheckOutDate());
			/*
			 * Checking if the room type in the request is different from the reservation's
			 * room type
			 */
			if (reservation.getRoomType() != request.getRoomType()) {
				// Fetching all rooms from the repository
				List<Room> room = roomRepo.findAll();
				// Filtering rooms based on availability and requested room type
				List<Room> filteredRoom = room.stream()
						.filter(roomType -> roomType.isAvailability() == true
								&& roomType.getRoomType().toString().equals(request.getRoomType().toString()))
						.collect(Collectors.toList());
				// If no rooms are available for the requested type
				if (filteredRoom.isEmpty()) {
					// Set a message indicating the requested room type is not available
					message = request.getRoomType() + " is not available";

				} else {
					/*
					 * If a room is available, perform the following actions: Get the previous room
					 * associated with the reservation
					 */
					Room previousRoom = roomRepo.getReferenceById(reservation.getRoomId());
					// Set the availability of the previous room to true
					previousRoom.setAvailability(true);
					// Save the changes to the previous room's availability
					roomRepo.save(previousRoom);
					// Update the reservation with the new room ID and room type
					reservation.setRoomId(filteredRoom.stream().findFirst().orElse(null).getRoomId());
					reservation.setRoomType(filteredRoom.stream().findFirst().orElse(null).getRoomType());
					// Set the availability of the new room to false
					filteredRoom.stream().findFirst().orElse(null).setAvailability(false);
					// Save the changes to the new room's availability
					roomRepo.save(filteredRoom.stream().findFirst().orElse(null));
					// Save the updated reservation details
					reservationRepo.save(reservation);
					// Set a message indicating successful reservation update
					message = "Reservation successfully updated";
				}

			}
		} else {
			// If the user IDs do not match, set a message indicating mismatched user
			message = "User not matched";
		}
		// Return the message indicating the result of the update operation
		return message;
	}

}
