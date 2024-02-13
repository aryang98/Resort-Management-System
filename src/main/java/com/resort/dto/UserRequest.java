package com.resort.dto;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

// Class representing a user request
public class UserRequest {

	// Private member variables representing various attributes of a user request

	// Unique identifier for the user
	private Long userId;

	// Enumerated type representing the user type using specified EnumType.STRING
	// strategy
	@Enumerated(EnumType.STRING)
	@NotNull(message="invalid userType:")
	private UserType userType;

	// First name of the user
	@NotEmpty(message="firstName must not be null:")
	private String firstName;

	// Last name of the user
	@NotEmpty(message="lastName must not be null:")
	private String lastName;

	// Email of the user
	@Email(message = "Email address is not valid:")
	@NotEmpty(message="gmail must not be null:")
	private String email;

	// Phone number of the user
	@Pattern(regexp = "^[6-9]\\d{9}$", message = "Invalid phone number format")
	private String phoneNo;

	// Password of the user
	@Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$", message = "Invalid password format")
	private String password;

	// Address of the user
	@NotEmpty
	private String address;

	// Hint question of the user
	@NotEmpty
	@Size(min = 6, message = "hintQuestion must be min of 6 characters:")
	private String hintQuestion;

	// Getter method to retrieve the user ID
	public Long getUserId() {
		return userId;
	}

	// Setter method to set the user ID
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	// Getter method to retrieve the first name
	public String getFirstName() {
		return firstName;
	}

	// Setter method to set the first name
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	// Getter method to retrieve the last name
	public String getLastName() {
		return lastName;
	}

	// Setter method to set the last name
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	// Getter method to retrieve the email
	public String getEmail() {
		return email;
	}

	// Setter method to set the email
	public void setEmail(String email) {
		this.email = email;
	}

	// Getter method to retrieve the phone number
	public String getPhoneNo() {
		return phoneNo;
	}

	// Setter method to set the phone number
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	// Getter method to retrieve the password
	public String getPassword() {
		return password;
	}

	// Setter method to set the password
	public void setPassword(String password) {
		this.password = password;
	}

	// Getter method to retrieve the address
	public String getAddress() {
		return address;
	}

	// Setter method to set the address
	public void setAddress(String address) {
		this.address = address;
	}

	// Getter method to retrieve the user type
	public UserType getUserType() {
		return userType;
	}

	// Setter method to set the user type
	public void setUserType(UserType userType) {
		this.userType = userType;
	}

	// Getter method to retrieve the hint question
	public String getHintQuestion() {
		return hintQuestion;
	}

	// Setter method to set the hint question
	public void setHintQuestion(String hintQuestion) {
		this.hintQuestion = hintQuestion;
	}

}
