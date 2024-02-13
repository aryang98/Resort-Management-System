// Declaring the package where the RoomRequest class resides
package com.resort.dto;

// Importing necessary Java classes and annotations
import java.util.List;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

// Class representing a room request
public class RoomRequest {

	// Private member variables representing various attributes of a room request

	// Unique identifier for the room
	private Long roomId;

	// Identifier for the user requesting the room
	@NotNull
	private Long userId;

	// Enumerated type representing the room type using specified EnumType.STRING
	// strategy
	@Enumerated(EnumType.STRING)
	@NotNull
	private RoomType roomType;

	// Capacity of the room
	@NotNull
	private int capacity;

	// List of amenities available in the room
    @NotEmpty
	private List<String> amenities;

	// Boolean indicating the availability status of the room
    @AssertTrue(message = "The boolean field must be true")
    private boolean availability;
	// Price of the room
	@NotNull(message="Price should be some value:")
	private double price;

	// Minimum price for the room
	@NotNull(message="minPrice should be some value:")
	private double minPrice;

	// Maximum price for the room
	@NotNull(message="maxPrice should be some value:")
	private double maxPrice;

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

	// Getter method to retrieve the user ID
	public Long getUserId() {
		return userId;
	}

	// Setter method to set the user ID
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// Getter method to retrieve the minimum price
	public double getMinPrice() {
		return minPrice;
	}

	// Setter method to set the minimum price
	public void setMinPrice(double minPrice) {
		this.minPrice = minPrice;
	}

	// Getter method to retrieve the maximum price
	public double getMaxPrice() {
		return maxPrice;
	}

	// Setter method to set the maximum price
	public void setMaxPrice(double maxPrice) {
		this.maxPrice = maxPrice;
	}
}
