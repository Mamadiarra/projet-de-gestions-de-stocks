package com.inexa.gestionstocksrestapi.interfaces;

import com.inexa.gestionstocksrestapi.model.Product;

import java.util.List;

public interface ProductInterface {

    public List<Product> listProduct();

    public void addProduct (Product product);
}
