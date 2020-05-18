package com.inexa.gestionstocksrestapi.Repository;

import com.inexa.gestionstocksrestapi.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    //List<Customer> findByNameContainingIgnoreCase(String name);
    @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE CONCAT('%',:name,'%')")
    List<Customer> findAllByNameWithLike(@Param("name") String name);

    List<Customer> findAllByNameContainingAndPhoneContainingAndEmailContainingAndLocationContaining(String name, String phone, String email, String location);
}
