package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Customer;

import java.util.List;

public interface CustomerService {

    public void addCustomer (Customer customer);

    public List<Customer> listCustomer();

    public void deleteById(Long id);

    public List<Customer> findByName(String name);

    public Customer findById (Long id);

    public List<Customer> findAllMultiplesFields(String name, String phone, String email, String location);
}
