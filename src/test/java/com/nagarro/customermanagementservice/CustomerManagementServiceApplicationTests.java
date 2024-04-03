package com.nagarro.customermanagementservice;

import com.nagarro.customermanagementservice.controller.CustomerController;
import com.nagarro.customermanagementservice.entity.Customer;
import com.nagarro.customermanagementservice.service.CustomerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CustomerManagementServiceApplicationTests {
	@InjectMocks
	private CustomerController customerController;
	@Mock
	private CustomerService customerService;

	@Test
	void contextLoads() {}

	@Test
	@DisplayName("Test case for adding Customer")
	void testAddCustomer() {
		Customer customer = new Customer();
		when(customerService.addCustomer(customer)).thenReturn(customer);

		ResponseEntity<Customer> responseEntity = customerController.addCustomer(customer);

		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertEquals(customer, responseEntity.getBody());
	}

	@Test
	@DisplayName("Test case for getting all Customers")
	public void testGetAllCustomers(){
		List<Customer> customers = Arrays.asList(
				new Customer(1L, "Saksham", "Joshi", "sakshamjoshi@gmail.com", "9870993764"),
				new Customer(2L, "Ashish", "Gupta", "ashishgupta@gmail.com", "9870993734"));
		when(customerService.getAllCustomers()).thenReturn(customers);

		ResponseEntity<List<Customer>> responseEntity = customerController.getAllCustomer();

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(customers, responseEntity.getBody());
	}

	@Test
	@DisplayName("Test case for getting Customer by customerId")
	void testGetCustomerById() {
		Long customerId = 1L;
		Customer customer = new Customer(1L, "Saksham", "Joshi", "sakshamjoshi@gmail.com", "9870993764");
		when(customerService.getCustomerById(customerId)).thenReturn(customer);

		ResponseEntity<Customer> responseEntity = customerController.getCustomerById(customerId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(customer, responseEntity.getBody());
	}

	@Test
	@DisplayName("Test case for updating Customer by customerId")
	void testUpdateCustomerDetails() {
		Long customerId = 1L;
		Customer customer = new Customer(1L, "Saksham", "Joshi", "sakshamjoshi@gmail.com", "9870993764");
		when(customerService.updateCustomerDetails(customerId, customer)).thenReturn(customer);

		ResponseEntity<Customer> responseEntity = customerController.updateCustomerDetails(customerId, customer);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(customer, responseEntity.getBody());
	}

	@Test
	@DisplayName("Test case for deleting Customer by customerId")
	void testDeleteCustomer() {
		Long customerId = 1L;

		ResponseEntity<Void> responseEntity = customerController.deleteCustomer(customerId);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		verify(customerService, times(1)).deleteCustomer(customerId);
	}

	@Test
	@DisplayName("Test case for getting Customer by email")
	void testGetCustomerByEmail() {
		String email = "test@example.com";
		Customer customer = new Customer(100L, "Test", "Test", "test@exmaple.com", "1234567890");
		when(customerService.getCustomerByEmail(email)).thenReturn(customer);

		ResponseEntity<Customer> responseEntity = customerController.getCustomerByEmail(email);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(customer, responseEntity.getBody());
	}
}
