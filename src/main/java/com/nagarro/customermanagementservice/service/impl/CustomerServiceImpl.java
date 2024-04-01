package com.nagarro.customermanagementservice.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.nagarro.customermanagementservice.entity.Customer;
import com.nagarro.customermanagementservice.exception.EmptyInputException;
import com.nagarro.customermanagementservice.exception.ResourceNotFoundException;
import com.nagarro.customermanagementservice.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	private List<Customer> customers = new ArrayList<>();

	@Override
	public void createInitial(List<Customer> listOfCustomers){
		customers.addAll(listOfCustomers);
	}

	@Override
	public Customer addCustomer(Customer customer) {

		// Checking if any of the fields is Empty
		if (customer.getEmail().isBlank() || customer.getFirstName().isBlank() || customer.getLastName().isBlank()) {
			throw new EmptyInputException("Input cannot be Null!", HttpStatus.BAD_REQUEST.value());
		}

		// Trimming the input
		customer.setFirstName(customer.getFirstName().trim());
		customer.setLastName(customer.getLastName().trim());
		customer.setEmail(customer.getEmail().trim());

		// Checking if the user with the same email already exists or not
		Optional<Customer> existingCustomerEmail = customers.stream()
				.filter(c -> c.getEmail().equals(customer.getEmail()))
				.findFirst();
		if (existingCustomerEmail.isPresent()) {
			throw new RuntimeException("Customer with email " + customer.getEmail() + " already exists!");
		}

		// Checking if the user with the same customerId already exists or not
		Optional<Customer> existingCustomerId = customers.stream()
				.filter(c -> c.getCustomerId().equals(customer.getCustomerId()))
				.findFirst();
		if (existingCustomerId.isPresent()) {
			throw new RuntimeException("Customer with customerId: " + customer.getCustomerId() + " already exists!");
		}

		customers.add(customer);
		return customer;
	}

	@Override
	public List<Customer> getAllCustomers() {
		return customers;
	}

	@Override
	public Customer getCustomerById(Long customerId) {
		Optional<Customer> customerOptional = customers.stream()
				.filter(c -> c.getCustomerId().equals(customerId))
				.findFirst();
		if (customerOptional.isPresent()) {
			return customerOptional.get();
		} else {
			throw new ResourceNotFoundException("Customer not Found with customerId: " + customerId,
					HttpStatus.NOT_FOUND.value());
		}
	}

	@Override
	public Customer updateCustomerDetails(Long customerId, Customer customer) {

		// Checking if any of the fields is Empty
		if (customer.getEmail().isBlank() || customer.getFirstName().isBlank() || customer.getLastName().isBlank()) {
			throw new EmptyInputException("Input cannot be Null!", HttpStatus.BAD_REQUEST.value());
		}

		// Trimming the input
		customer.setFirstName(customer.getFirstName().trim());
		customer.setLastName(customer.getLastName().trim());
		customer.setEmail(customer.getEmail().trim());

		// Checking if the user with the customerId exists or not
		Optional<Customer> customerOptional = customers.stream()
				.filter(c -> c.getCustomerId().equals(customerId))
				.findFirst();
		if (customerOptional.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Found with id: " + customerId,
					HttpStatus.NOT_FOUND.value());
		}

		// Updating the customer details
		Customer existingCustomer = customerOptional.get();
		existingCustomer.setEmail(customer.getEmail());
		existingCustomer.setFirstName(customer.getFirstName());
		existingCustomer.setLastName(customer.getLastName());

		customers.add(existingCustomer);
		return existingCustomer;
	}

	@Override
	public void deleteCustomer(Long customerId) {

		// Checking if the user with the customerId exists or not
		Optional<Customer> customerOptional = customers.stream()
				.filter(c -> c.getCustomerId().equals(customerId))
				.findFirst();
		if (customerOptional.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Found with id: " + customerId,
					HttpStatus.NOT_FOUND.value());
		}

		Customer existingCustomer = customerOptional.get();

		customers.remove(existingCustomer);
	}

	@Override
	public Customer getCustomerByEmail(String email) {

		// Checking if the user with the email exists or not
		Optional<Customer> customerOptional = customers.stream()
				.filter(c -> c.getEmail().equals(email))
				.findFirst();
		if (customerOptional.isEmpty()) {
			throw new ResourceNotFoundException("Customer not Found with email: " + email,
					HttpStatus.NOT_FOUND.value());
		}

        return customerOptional.get();
	}
}
