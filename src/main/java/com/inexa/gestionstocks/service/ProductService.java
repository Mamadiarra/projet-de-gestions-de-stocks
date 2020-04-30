package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

}
