package com.resort.entity;

import java.util.Date;
import com.resort.dto.ReservationRequest;
import com.resort.dto.RoomType;
import com.resort.dto.Status;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;

// Annotation to mark this class as an entity in the database
@Entity
public class Reservation {
	// Unique identifier for Reservation entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservationId;

	// Many-to-One association with UserEntity, mapped by the userId field
	@ManyToOne
	@JoinColumn(name = "userId")
	private UserEntity user;

	/*
	 * Enumerated type representing the room type using specified EnumType.STRING
	 * strategy
	 */
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	// Identifier for the room
	private Long roomId;

	// Date for check-in
	private Date checkInDate;

	// Date for check-out
	private Date checkOutDate;

	// Enumerated type representing the status of the reservation
	@Enumerated(EnumType.STRING)
	private Status status;

	// One-to-One association with Payment entity, cascading all operations
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "payment_id", referencedColumnName = "paymentId")
	private Payment payment;

	// Default constructor for the Reservation class
	public Reservation() {
		// Invoking the constructor of the superclass (Object class)
		super();
	}

	/*
	 * Constructor that takes a ReservationRequest object as a parameter and
	 * initializes Reservation fields
	 */
	public Reservation(ReservationRequest request) {
		// Invoking the constructor of the superclass (Object class)
		super();

		// Setting various fields from the ReservationRequest object
		this.reservationId = request.getReservationId();
		this.user = request.getUser();
		this.roomId = request.getRoomId();
		this.roomType = request.getRoomType();
		this.checkInDate = request.getCheckInDate();
		this.checkOutDate = request.getCheckOutDate();
		this.status = request.getStatus();
		this.payment = request.getPayment();
	}

	// Getter method to retrieve the room type
	public RoomType getRoomType() {
		return roomType;
	}

	// Setter method to set the room type
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	// Getter method to retrieve the reservation ID
	public Long getReservationId() {
		return reservationId;
	}

	// Setter method to set the reservation ID
	public void setReservationId(Long reservationId) {
		this.reservationId = reservationId;
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

	// Getter method to retrieve the status of the reservation
	public Status getStatus() {
		return status;
	}

	// Setter method to set the status of the reservation
	public void setStatus(Status status) {
		this.status = status;
	}

	// Getter method to retrieve the payment details
	public Payment getPayment() {
		return payment;
	}

	// Setter method to set the payment details
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	// Getter method to retrieve the associated user entity
	public UserEntity getUser() {
		return user;
	}

	// Setter method to set the associated user entity
	public void setUser(UserEntity user) {
		this.user = user;
	}
}
