package com.inexa.gestionstocksrestapi.Service;

import com.inexa.gestionstocksrestapi.Repository.ProductRepository;
import com.inexa.gestionstocksrestapi.interfaces.ProductInterface;
import com.inexa.gestionstocksrestapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements ProductInterface {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> listProduct() {
        return productRepository.findAll();
    }

    @Override
    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
