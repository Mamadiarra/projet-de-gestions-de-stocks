package com.inexa.gestionstocks;

import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

@SpringBootTest
public class CustomerTest {

    @Autowired
    public CustomerService defaultService;

    @Test
    void list_customers()
    {
        List<Customer> customers = defaultService.listCustomerForClientDatatable();

        assertTrue(customers.size() > 0);
    }

    @Test
    void find_by_id()
    {
        long customerId = 1;

        Customer customer = defaultService.findById(customerId);

        Assert.notNull(customer, "Must not be null");
    }

}
