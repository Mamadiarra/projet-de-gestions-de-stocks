package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.exception.CustomerNotFoundException;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("defaultService")
public class CustomerServiceImpl implements CustomerService{

    @Autowired
    private CustomerRepository customerRepository;

    public void addCustomer(Customer customer)
    {
        customerRepository.save(customer);
    }

    public Page<Customer> listCustomerWithPagination(Pageable pageable)
    {
        return customerRepository.findAll(pageable);
    }

    @Override
    public List<Customer> listCustomerForClientDatatable() {
        return customerRepository.findAll();
    }

    public void deleteById(Long id)
    {
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> findByName(String name) {
        //return customerRepository.findByNameContainingIgnoreCase(name);
        return customerRepository.findAllByNameWithLike(name);
    }

    @Override
    public Customer findById(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Override
    public List<Customer> findAllMultiplesFields(String name, String phone, String email, String location) {
        return customerRepository.findAllByNameContainingAndPhoneContainingAndEmailContainingAndLocationContaining(name, phone, email, location);
    }

}
