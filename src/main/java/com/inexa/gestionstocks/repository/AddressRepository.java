package com.inexa.gestionstocks.repository;

import com.inexa.gestionstocks.model.Address;
import com.inexa.gestionstocks.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByCustomer(Customer customer);

}
