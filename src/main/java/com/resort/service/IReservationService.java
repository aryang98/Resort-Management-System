package com.resort.service;

// Importing necessary classes and entities
import java.util.List;
import java.util.Optional;

import com.resort.dto.ReservationRequest;
import com.resort.entity.Reservation;

// Interface representing Reservation Service
public interface IReservationService {

	// Method to fetch all reservations based on the provided ReservationRequest
	List<Reservation> fetchAllReservation(ReservationRequest request);

	// Method to make a reservation based on the provided ReservationRequest
	String makeReservation(ReservationRequest request);

	// Method to get a reservation by its unique ID
	Optional<Reservation> getReservationById(Long id);

	// Method to update a reservation status based on the provided
	// ReservationRequest
	String updateStatus(ReservationRequest request);

	// Method to update a reservation status based on the provided
	// ReservationRequest
	String updateReservation(ReservationRequest request);

	// Method to make a payment for a reservation based on the provided
	// ReservationRequest
	String makePayment(ReservationRequest request);
}
