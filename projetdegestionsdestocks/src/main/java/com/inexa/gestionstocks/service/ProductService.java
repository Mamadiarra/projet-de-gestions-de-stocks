package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> productList() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    private String getProductImagePath() {
        return new File("C:\\projectjee\\projetdegestionsdestocks\\src\\main\\resources\\static\\images").getAbsolutePath();
    }

    public String storeFileOnDisk(MultipartFile fileImage) throws IOException {

        String folder = this.getProductImagePath();

        byte[] bytes = fileImage.getBytes();

        String fileName = fileImage.getOriginalFilename();

        Path path = Paths.get(folder + "/products/" + fileImage.getOriginalFilename());

        Files.write(path, bytes);

        return fileName;
    }

    public void addProduct(Product product)
    {
        productRepository.save(product);
    }

}
