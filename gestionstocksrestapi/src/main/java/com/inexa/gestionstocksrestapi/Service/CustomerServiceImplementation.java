package com.inexa.gestionstocksrestapi.Service;

import com.inexa.gestionstocksrestapi.Repository.CustomerRepository;
import com.inexa.gestionstocksrestapi.model.Customer;
import com.inexa.gestionstocksrestapi.model.CustomerUpdateOnlyName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImplementation implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    @Override
    public List<Customer> listCustomer() {
        return customerRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        //return customerRepository.findByNameContainingIgnoreCase(name);
        return customerRepository.findAllByNameWithLike(name);
    }

    @Override
    public Optional<Customer> findById(Long id) {
        return customerRepository.findById(id);
        //return null;
    }

    @Override
    public List<Customer> findAllMultiplesFields(String name, String phone, String email, String location) {
        return customerRepository.findAllByNameContainingAndPhoneContainingAndEmailContainingAndLocationContaining(name, phone, email, location);
    }
}
