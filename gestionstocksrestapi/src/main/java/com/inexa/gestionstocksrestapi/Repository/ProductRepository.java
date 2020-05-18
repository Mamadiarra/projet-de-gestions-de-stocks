package com.inexa.gestionstocksrestapi.Repository;

import com.inexa.gestionstocksrestapi.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
