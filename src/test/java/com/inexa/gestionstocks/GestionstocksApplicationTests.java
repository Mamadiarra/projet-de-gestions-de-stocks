package com.inexa.gestionstocks;

import com.inexa.gestionstocks.Form.CustomerForm;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class GestionstocksApplicationTests {

	@Autowired
	public CustomerService defaultService;
	/*
	@Test
	void test_get_all_customers()
	{
		// Step 1 : Init param
		// Step 2 : Call Service with parameter
		// Step 3 : Save response
		// Step 3 : assert
		List<Customer> customers = defaultService.listCustomer();

		int customerNumber = defaultService.listCustomer().size();

		assertTrue(customerNumber > 0);
	}

	@Test
	void insert_customer_in_database()
	{
		List<Customer> customers = defaultService.listCustomer();

		Customer customer = new Customer();

		int number = customers.size() + 1;

		customer.setName("Diarra Mamadou"+number);

		customer.setEmail("Test"+number+"@gmail.com");

		customer.setLocation("Location"+number);

		customer.setPhone("09876543");

		defaultService.addCustomer(customer);

		int customerNumber = defaultService.listCustomer().size();

		//assertThat(customers).hasSize(customerNumber);
	}
	*/
}
