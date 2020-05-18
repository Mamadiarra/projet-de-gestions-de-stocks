package com.inexa.gestionstocksihm.controller;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import com.inexa.gestionstocksihm.form.ProductForm;
import com.inexa.gestionstocksihm.model.Product;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class ProductController {
    
    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list-product")
    public String listProduct(Model model) {
        List<ProductForm> products = callApi();

        model.addAttribute("products", products);

        return "listProduct";
    }

    @GetMapping("/add-product")
    public String addProduct(ProductForm productForm) {
        return "addProduct";
    }

    @PostMapping("/add-product")
    public String storeCustomer(@Valid ProductForm productForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addProduct";
        }

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:9090/add-product";

        Product product = new Product();

        product.setName(productForm.getName());
        product.setNumberProduct(productForm.getNumberProduct());
        product.setPrice(productForm.getPrice());
        product.setDescription(productForm.getDescription());

        HttpEntity<Product> request = new HttpEntity<>(product);

        ResponseEntity<Product> response = restTemplate.exchange(resourceUrl, HttpMethod.POST, request,
            Product.class);
        
        if (response.getStatusCode() != HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("error", "true");
            return "addProduct";
        }

        redirectAttributes.addFlashAttribute("success", "true");
        
        return "redirect:/list-product";
    }

    private List<ProductForm> callApi()
    {
        List<ProductForm> products =  new ArrayList<ProductForm>();

        RestTemplate restTemplate = new RestTemplate();
        
        String resourceUrl = "http://localhost:9090/list-product";

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
        // Make the HTTP GET request, marshaling the response from JSON to an array of Events
        final ProductForm[] productFormTable = restTemplate.getForObject(resourceUrl, ProductForm[].class);
        
        products = Arrays.asList(productFormTable);

        return products;
    }

}