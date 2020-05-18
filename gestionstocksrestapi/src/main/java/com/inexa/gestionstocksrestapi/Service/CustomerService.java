package com.inexa.gestionstocksrestapi.Service;

import com.inexa.gestionstocksrestapi.model.Customer;

import java.util.List;
import java.util.Optional;

public interface CustomerService {

    public void addCustomer (Customer customer);

    public List<Customer> listCustomer();

    public void deleteById(Long id);

    public List<Customer> findByName(String name);

    public Optional<Customer> findById (Long id);

    public List<Customer> findAllMultiplesFields(String name, String phone, String email, String location);
}
