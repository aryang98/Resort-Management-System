// Declaring the package where the ReservationRequest class resides
package com.resort.dto;

// Importing necessary Java classes and entities
import java.util.Date;
import com.resort.entity.Payment;
import com.resort.entity.UserEntity;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;

// Class representing a reservation request
public class ReservationRequest {

	// Private member variables representing various attributes of a reservation
	// request

	// Unique identifier for the reservation
	private Long reservationId;

	// UserEntity object representing the user associated with the reservation
	private UserEntity user;

	// Enum representing the room type for the reservation
	@NotNull
	private RoomType roomType;

	// Identifier for the room requested in the reservation
	private Long roomId;

	// Date for the check-in of the reservation
	@FutureOrPresent
	private Date checkInDate;

	// Date for the check-out of the reservation
	@Future
	private Date checkOutDate;

	// Enum representing the status of the reservation
	private Status status;

	// Payment object associated with the reservation
	private Payment payment;

	// Getter method to retrieve the reservation ID
	public Long getReservationId() {
		return reservationId;
	}

	// Setter method to set the reservation ID
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
	}

	// Getter method to retrieve the room type
	public RoomType getRoomType() {
		return roomType;
	}

	// Setter method to set the room type
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	// Getter method to retrieve the check-in date
	public Date getCheckInDate() {
		return checkInDate;
	}

	// Setter method to set the check-in date
	public void setCheckInDate(Date checkInDate) {
		this.checkInDate = checkInDate;
	}

	// Getter method to retrieve the check-out date
	public Date getCheckOutDate() {
		return checkOutDate;
	}

	// Setter method to set the check-out date
	public void setCheckOutDate(Date checkOutDate) {
		this.checkOutDate = checkOutDate;
	}

	// Getter method to retrieve the room ID
	public Long getRoomId() {
		return roomId;
	}

	// Setter method to set the room ID
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	// Getter method to retrieve the reservation status
	public Status getStatus() {
		return status;
	}

	// Setter method to set the reservation status
	public void setStatus(Status status) {
		this.status = status;
	}

	// Getter method to retrieve the payment associated with the reservation
	public Payment getPayment() {
		return payment;
	}

	// Setter method to set the payment associated with the reservation
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// Getter method to retrieve the user associated with the reservation
	public UserEntity getUser() {
		return user;
	}

	// Setter method to set the user associated with the reservation
	public void setUser(UserEntity user) {
		this.user = user;
	}

}
