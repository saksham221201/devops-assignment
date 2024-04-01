package com.nagarro.customermanagementservice.service;

import java.util.List;

import com.nagarro.customermanagementservice.entity.Customer;

public interface CustomerService {
	void createInitial(List<Customer> listOfCustomers);
	Customer addCustomer(Customer customer);
	List<Customer> getAllCustomers();
	Customer getCustomerByEmail(String email);
	Customer getCustomerById(Long customerId);
	Customer updateCustomerDetails(Long customerId, Customer customer);
	void deleteCustomer(Long customerId);
}
