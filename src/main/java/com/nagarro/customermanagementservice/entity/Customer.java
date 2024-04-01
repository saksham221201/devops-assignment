package com.nagarro.customermanagementservice.entity;

import com.nagarro.customermanagementservice.constant.Constant;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

public class Customer {

	private Long customerId;
	private String firstName;
	private String lastName;

	@Email(regexp = Constant.REGEX_PATTERN, message = Constant.EMAIL_MESSAGE)
	private String email;

	@Size(min = Constant.PHONE_NUMBER_LENGTH, max = Constant.PHONE_NUMBER_LENGTH, 
	message = Constant.PHONE_NUMBER_MESSAGE)
	private String phoneNumber;

	// Default Constructor
	public Customer() {
		super();
	}

	// Parameterized Constructor
	public Customer(Long customerId, String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	// Getters and Setters
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
}
