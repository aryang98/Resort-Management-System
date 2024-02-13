package com.resort.controller;

import java.util.List;

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

//This class serves as a REST controller mapped to '/home'
@RestController
@RequestMapping("/home")

/**
 * 
 * @author akuma291
 *
 */
public class ReservationController {
	// Autowiring the IReservation Service bean
	@Autowired(required = true)
	private IReservationService reservationService;

//Admin features to see all reservation
	@GetMapping("/reservations")
	public ResponseEntity<GeneralResponseDTO> getAllReservations(@RequestBody ReservationRequest request) {
		// Fetching all reservations using Reservation Service
		List<Reservation> reservations = reservationService.fetchAllReservation(request);
		// Creating a response DTO containing the list of reservations
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("List of reservations", reservations);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

// Geting reservation by specific reservation id
	@GetMapping("/reservation")
	public ResponseEntity<GeneralResponseDTO> getReservationById(@RequestParam Long id) {
		// Retrieving a reservation by ID using Reservation Service
		Reservation reservation = reservationService.getReservationById(id).get();
		// Creating a response DTO containing details of the selected reservation
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("Details of selected reservation ", reservation);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

// reservation done by User 
	@PostMapping("/addReservation")
	public ResponseEntity<GeneralResponseDTO> makeReservation(@Valid @RequestBody ReservationRequest request) {
		// Making a reservation using Reservation Service
		String message = reservationService.makeReservation(request);
		// Creating a response DTO containing a message regarding the reservation
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

//Admin access to update reservation like accept or reject booking request
	@PutMapping("/updateStatus")
	public ResponseEntity<GeneralResponseDTO> updateStatus(@RequestBody ReservationRequest request) {
		// Updating reservation details using Reservation Service
		String message = reservationService.updateStatus(request);
		// Creating a response DTO containing a message regarding the update
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

	// User access to update reservation
	@PutMapping("/updateReservation")
	public ResponseEntity<GeneralResponseDTO> updateReservation(@Valid @RequestBody ReservationRequest request) {
		// Updating reservation details using Reservation Service
		String message = reservationService.updateReservation(request);
		// Creating a response DTO containing a message regarding the update
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}

//user access to make payment after booking the room 
	@PutMapping("/makePayment")
	public ResponseEntity<GeneralResponseDTO> makePayment(@RequestBody ReservationRequest request) {
		// Making payment after booking using Reservation Service
		String message = reservationService.makePayment(request);
		// Creating a response DTO containing a message regarding the payment
		GeneralResponseDTO responseDTO = new GeneralResponseDTO("message", message);
		// Returning a ResponseEntity with the response DTO and HTTP status OK
		return new ResponseEntity<GeneralResponseDTO>(responseDTO, HttpStatus.OK);
	}
}
