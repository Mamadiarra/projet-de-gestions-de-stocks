package com.inexa.gestionstocks;

import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class ProductTest {

    @Autowired
    public ProductService productService;

    @Test
    void list_customers()
    {
        List<Product> products = productService.productList();

        assertTrue(products.size() > 0);
    }

    @Test
    void find_by_id()
    {
        long productId = 2;

        Product product = productService.findById(productId);

        Assert.notNull(product, "Must not be null");
    }

}
