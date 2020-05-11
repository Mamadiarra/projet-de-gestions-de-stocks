package com.inexa.gestionstocks.repository;

import com.inexa.gestionstocks.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

    List<Order> findAllByStatus(Integer status);
}
