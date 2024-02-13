package com.resort.entity;

import java.util.List;
import com.resort.dto.RoomRequest;
import com.resort.dto.RoomType;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Annotation to mark this class as an entity in the database
@Entity
public class Room {
	// Unique identifier for Room entity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long roomId;

	/*
	 * Enumerated type representing the room type using specified EnumType.STRING
	 * strategy
	 */
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	// Capacity of the room
	private int capacity;

	// List of amenities available in the room
	private List<String> amenities;

	// Boolean indicating the availability status of the room
	private boolean availability;

	// Price of the room
	private double price;

	// Default constructor for the Room class required by JPA
	public Room() {
		super();
	}

	/*
	 * Constructor that takes a RoomRequest object as a parameter and initializes
	 * Room fields
	 */
	public Room(RoomRequest request) {
		super();
		this.roomId = request.getRoomId();
		this.roomType = request.getRoomType();
		this.capacity = request.getCapacity();
		this.amenities = request.getAmenities();
		this.availability = request.isAvailability();
		this.price = request.getPrice();
	}

	// Getter method to retrieve the room ID
	public Long getRoomId() {
		return roomId;
	}

	// Setter method to set the room ID
	public void setRoomId(Long roomId) {
		this.roomId = roomId;
	}

	// Getter method to retrieve the room type
	public RoomType getRoomType() {
		return roomType;
	}

	// Setter method to set the room type
	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	// Getter method to retrieve the room capacity
	public int getCapacity() {
		return capacity;
	}

	// Setter method to set the room capacity
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}

	// Getter method to retrieve the list of amenities
	public List<String> getAmenities() {
		return amenities;
	}

	// Setter method to set the list of amenities
	public void setAmenities(List<String> amenities) {
		this.amenities = amenities;
	}

	// Getter method to check room availability
	public boolean isAvailability() {
		return availability;
	}

	// Setter method to set room availability
	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

	// Getter method to retrieve the room price
	public double getPrice() {
		return price;
	}

	// Setter method to set the room price
	public void setPrice(double price) {
		this.price = price;
	}

}
