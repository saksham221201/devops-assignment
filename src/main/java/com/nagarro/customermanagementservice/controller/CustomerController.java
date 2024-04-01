package com.nagarro.customermanagementservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nagarro.customermanagementservice.entity.Customer;
import com.nagarro.customermanagementservice.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/api/customers")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@PostMapping("/add")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer){
		Customer addedCustomer = customerService.addCustomer(customer);
		return new ResponseEntity<>(addedCustomer, HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<List<Customer>> getAllCustomer(){
		List<Customer> customers = customerService.getAllCustomers();
		return new ResponseEntity<>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId){
		Customer customer = customerService.getCustomerById(customerId);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
	
	@PutMapping("/{customerId}")
	public ResponseEntity<Customer> updateCustomerDetails(@Valid @PathVariable Long customerId, @RequestBody Customer customer){
		Customer updatedCustomer = customerService.updateCustomerDetails(customerId, customer);
		return new ResponseEntity<>(updatedCustomer, HttpStatus.OK);
	}
	
	@DeleteMapping("/{customerId}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long customerId){
		customerService.deleteCustomer(customerId);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@GetMapping("email/{email}")
	public ResponseEntity<Customer> getCustomerByEmail(@PathVariable String email){
		Customer customer = customerService.getCustomerByEmail(email);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}
}
