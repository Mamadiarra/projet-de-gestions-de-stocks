package com.inexa.gestionstocksrestapi.controller;

import com.inexa.gestionstocksrestapi.Service.CustomerService;
import com.inexa.gestionstocksrestapi.Tools.ApiTools;
import com.inexa.gestionstocksrestapi.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomerController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ApiTools apiTools;

    /**
     * Cette fonction retourne la liste des clients en format JSON
     * @return un format JSON
     */
    @GetMapping("/list-customer")
    public List<Customer> listCustomer()
    {
        log.info("Call api list customer");

        List<Customer> customers = customerService.listCustomer();

        return customers;
    }

    @PostMapping("add-customer")
    public ResponseEntity addCustomer(@RequestBody Customer customer)
    {
        log.info("Call api post customer");

        customerService.addCustomer(customer);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/get-customer/{id}")
    public ResponseEntity<Object> getCustomerById(@PathVariable long id) {

        Optional<Customer> findCustomer = customerService.findById(id);

        if (!findCustomer.isPresent())
            return ResponseEntity.notFound().build();

        return new ResponseEntity<>(findCustomer, HttpStatus.OK);
    }

    @PutMapping("/update-customer/{id}")
    public ResponseEntity<Object> updateCustomer(@RequestBody Customer customer, @PathVariable long id) {

        Optional<Customer> findCustomer = customerService.findById(id);

        if (!findCustomer.isPresent())
            return ResponseEntity.notFound().build();

        customer.setId(id);

        customerService.addCustomer(customer);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/update-one-field-customer/{id}")
    public ResponseEntity<Object> updateCustumerOneField(@RequestBody Customer customer, @PathVariable long id) {

        Optional<Customer> findCustomer = customerService.findById(id);

        if (!findCustomer.isPresent())
            return ResponseEntity.notFound().build();

        customer.setId(id);

        Customer mergeObject = apiTools.mergeObject(findCustomer.get(), customer);

        customerService.addCustomer(mergeObject);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/delete-customer/{id}")
    public ResponseEntity deleteStudent(@PathVariable long id) {
        log.info("Param id "+id);
        customerService.deleteById(id);
        return new ResponseEntity(HttpStatus.OK);
    }
}
