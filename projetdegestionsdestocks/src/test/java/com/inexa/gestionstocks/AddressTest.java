package com.inexa.gestionstocks;

import com.inexa.gestionstocks.model.Address;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.AddressService;
import com.inexa.gestionstocks.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class AddressTest {

    @Autowired
    private CustomerService defaultService;
    @Autowired
    private AddressService addressService;

    @Test
    void list_address_for_customer()
    {
        long customerId = 1;

        Customer customer = defaultService.findById(customerId);

        List<Address> addresses = addressService.listAddressByCustomer(customer);

        assertTrue(addresses.size() > 0);
    }


    @Test
    void find_address_by_id()
    {
        long addressId = 22;

        Optional<Address> address = addressService.findById(addressId);

        //assertTrue(address.get());

        Assert.notNull(address.get(), "Must not be null");
    }

}
