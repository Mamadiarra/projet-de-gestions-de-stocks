package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    public void addCustomer (Customer customer);

    public Page<Customer> listCustomerWithPagination(Pageable pageable);

    public List<Customer> listCustomerForClientDatatable();

    public void deleteById(Long id);

    public List<Customer> findByName(String name);

    public Customer findById (Long id);

    public List<Customer> findAllMultiplesFields(String name, String phone, String email, String location);
}
