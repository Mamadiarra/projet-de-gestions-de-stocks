package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerRestController {

    @Autowired
    private CustomerService defaultService;

    /**
     * Cette fonction retourne la liste des clients en format JSON
     * @return
     */
    @GetMapping("/list-customer-side-server")
    public List<Customer> getAllCustomerRest()
    {
        return defaultService.listCustomerForClientDatatable();
    }
}
