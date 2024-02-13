package com.resort.entity;

import com.resort.dto.PaymentRequest;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

// Entity class representing payments
@Entity
public class Payment {
	// Declaring member variables for Payment entity

	// Unique identifier for the payment entity, annotated as the primary key
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long paymentId;

	// String to store the payment mode (e.g., credit card, cash, etc.)
	private String paymentMode;

	// String to store the payment status (e.g., pending, completed, etc.)
	private String paymentStatus;

	// Default constructor for the Payment entity class
	public Payment() {
		// Invoking the constructor of the superclass (Object class)
		super();
	}

	/*
	 * Parameterized constructor for Payment entity, accepting a PaymentRequest
	 * object
	 */
	public Payment(PaymentRequest request) {
		// Invoking the constructor of the superclass (Object class)
		super();

		// Assigning values from PaymentRequest object to respective member variables
		this.paymentId = request.getPaymentId();
		this.paymentMode = request.getPaymentMode();
		this.paymentStatus = request.getPaymentStatus();
	}

	// Getter method to retrieve the payment mode
	public String getPaymentMode() {
		return paymentMode;
	}

	// Setter method to set the payment mode
	public void setPaymentMode(String paymentMode) {
		this.paymentMode = paymentMode;
	}

	// Getter method to retrieve the payment status
	public String getPaymentStatus() {
		return paymentStatus;
	}

	// Setter method to set the payment status
	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	// Getter method to retrieve the payment ID
	public Long getPaymentId() {
		return paymentId;
	}

	// Setter method to set the payment ID
	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}
}
