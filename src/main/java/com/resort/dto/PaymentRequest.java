// Declaring the package where the PaymentRequest class resides
package com.resort.dto;

// Class representing a payment request
public class PaymentRequest {

	// Private member variables representing various attributes of a payment request

	// Unique identifier for the payment
	private Long paymentId;

	// String variable representing the payment mode
	private String paymentMode;

	// String variable representing the payment status
	private String paymentStatus;

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
