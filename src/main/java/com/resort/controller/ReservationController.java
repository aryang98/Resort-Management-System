package com.resort.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.resort.dto.GeneralResponseDTO;
import com.resort.dto.ReservationRequest;
import com.resort.entity.Reservation;
import com.resort.service.IReservationService;

import jakarta.validation.Valid;

// This class serves as a REST controller for handling reservation-related requests
@RestController
@RequestMapping("/home")
public class ReservationController {

	// Logger instance for logging
	private static final Logger logger = LoggerFactory.getLogger(ReservationController.class);

	@Autowired(required = true)
	private IReservationService reservationService;

	// Get all reservations (admin feature)
	@GetMapping("/reservations")
	public ResponseEntity<GeneralResponseDTO> getAllReservations(@RequestBody ReservationRequest request) {
		try {
			logger.info("Fetching all reservations...");
			List<Reservation> reservations = reservationService.fetchAllReservation(request);
			logger.info("Fetched {} reservations", reservations.size());
			return new ResponseEntity<>(new GeneralResponseDTO("List of reservations", reservations), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while fetching reservations: {}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Get reservation by ID
	@GetMapping("/reservation")
	public ResponseEntity<GeneralResponseDTO> getReservationById(@RequestParam Long id) {
		try {
			logger.info("Fetching reservation with ID: {}", id);
			Reservation reservation = reservationService.getReservationById(id).get();
			logger.info("Fetched reservation: {}", reservation);
			return new ResponseEntity<>(new GeneralResponseDTO("Details of selected reservation ", reservation),
					HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while fetching reservation with ID {}: {}", id, e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Make reservation
	@PostMapping("/addReservation")
	public ResponseEntity<GeneralResponseDTO> makeReservation(@Valid @RequestBody ReservationRequest request) {
		try {
			logger.info("Making reservation: {}", request);
			String message = reservationService.makeReservation(request);
			logger.info("Reservation made successfully: {}", message);
			return new ResponseEntity<>(new GeneralResponseDTO("message", message), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while making reservation: {}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update reservation status (admin feature)
	@PutMapping("/updateStatus")
	public ResponseEntity<GeneralResponseDTO> updateStatus(@RequestBody ReservationRequest request) {
		try {
			logger.info("Updating reservation status: {}", request);
			String message = reservationService.updateStatus(request);
			logger.info("Reservation status updated successfully: {}", message);
			return new ResponseEntity<>(new GeneralResponseDTO("message", message), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating reservation status: {}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Update reservation (user feature)
	@PutMapping("/updateReservation")
	public ResponseEntity<GeneralResponseDTO> updateReservation(@Valid @RequestBody ReservationRequest request) {
		try {
			logger.info("Updating reservation: {}", request);
			String message = reservationService.updateReservation(request);
			logger.info("Reservation updated successfully: {}", message);
			return new ResponseEntity<>(new GeneralResponseDTO("message", message), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while updating reservation: {}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Make payment (user feature)
	@PutMapping("/makePayment")
	public ResponseEntity<GeneralResponseDTO> makePayment(@RequestBody ReservationRequest request) {
		try {
			logger.info("Making payment: {}", request);
			String message = reservationService.makePayment(request);
			logger.info("Payment made successfully: {}", message);
			return new ResponseEntity<>(new GeneralResponseDTO("message", message), HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error occurred while making payment: {}", e.getMessage(), e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}