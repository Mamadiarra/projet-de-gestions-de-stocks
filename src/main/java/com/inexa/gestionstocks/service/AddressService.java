package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Address;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService implements AddressServiceInterface {

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public List<Address> listAddressByCustomer(Customer customer) {
        return addressRepository.findByCustomer(customer);
    }

    @Override
    public void addAddress(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void deleteById(Long id) {
        addressRepository.deleteById(id);
    }

    @Override
    public Optional<Address> findById(Long id) {
        return addressRepository.findById(id);
    }
}
