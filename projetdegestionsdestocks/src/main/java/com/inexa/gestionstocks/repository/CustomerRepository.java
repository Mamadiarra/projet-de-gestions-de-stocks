package com.inexa.gestionstocks.repository;

import com.inexa.gestionstocks.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    //List<Customer> findByNameContainingIgnoreCase(String name);

    @Query(value = "SELECT c FROM Customer c WHERE c.name LIKE CONCAT('%',:name,'%')")
    List<Customer> findAllByNameWithLike(@Param("name") String name);

    List<Customer> findAllByNameContainingAndPhoneContainingAndEmailContainingAndLocationContaining(String name, String phone, String email, String location);
}
