package com.resort.entity;

import com.resort.dto.UserRequest;
import com.resort.dto.UserType;

import jakarta.persistence.*;

// Annotation to mark this class as an entity in the database
@Entity
@Table(name = "user", uniqueConstraints = { @UniqueConstraint(columnNames = "email"),
		@UniqueConstraint(columnNames = "phoneNo") })
public class UserEntity {

	// Unique identifier for UserEntity
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Automatically generated ID
	@Column(name = "userId") // Maps this field to the column named "userId" in the database
	private Long userId;

	/*
	 * Enumerated type representing the user type using specified EnumType.STRING
	 * strategy
	 */
	@Enumerated(EnumType.STRING)
	private UserType userType;

	// First name of the user
	private String firstName;

	// Last name of the user
	private String lastName;

	// Email of the user
	private String email;

	// Phone number of the user
	private String phoneNo;

	// Password of the user
	private String password;

	// Address of the user
	private String address;

	// Hint question of the user
	private String hintQuestion;

	// Default constructor for the UserEntity class
	public UserEntity() {
		// Invoking the constructor of the superclass (Object class)
		super();
	}

	/*
	 * Constructor that takes a UserRequest object as a parameter and initializes
	 * UserEntity fields
	 */
	public UserEntity(UserRequest request) {
		// Invoking the constructor of the superclass (Object class)
		super();

		// Setting various fields from the UserRequest object
		this.userType = request.getUserType();
		this.firstName = request.getFirstName();
		this.lastName = request.getLastName();
		this.email = request.getEmail();
		this.phoneNo = request.getPhoneNo();
		this.password = request.getPassword();
		this.address = request.getAddress();
		this.hintQuestion = request.getHintQuestion();
	}

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
