package com.inexa.gestionstocksrestapi.controller;

import com.inexa.gestionstocksrestapi.Service.ProductService;
import com.inexa.gestionstocksrestapi.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/list-product")
    public List<Product> listProduct()
    {
        return productService.listProduct();
    }

    @PostMapping("add-product")
    public ResponseEntity addProduct(@RequestBody Product product)
    {
        productService.addProduct(product);

        return new ResponseEntity(HttpStatus.OK);
    }

}
