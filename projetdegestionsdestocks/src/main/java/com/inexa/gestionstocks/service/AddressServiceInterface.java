package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Address;
import com.inexa.gestionstocks.model.Customer;

import java.util.List;
import java.util.Optional;

public interface AddressServiceInterface {

    List<Address> listAddressByCustomer(Customer customer);

    void addAddress (Address address);

    void deleteById(Long id);

    public Optional<Address> findById(Long id);
}
