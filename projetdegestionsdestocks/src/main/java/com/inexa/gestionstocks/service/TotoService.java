package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service("totoService")
public class TotoService /*implements CustomerService*/ {

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer)
    {
        customerRepository.save(customer);
    }

    public List<Customer> listCustomer()
    {
        return (List<Customer>) customerRepository.findAll();
    }

    public void deleteById(Long id)
    {
        customerRepository.deleteById(id);
    }

    public List<Customer> findByName(String name) {
        //return customerRepository.findByNameContainingIgnoreCase(name);
        return null;
    }

    public Optional<Customer> findById(Long id) {
        return Optional.empty();
    }

}
