package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ProductController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProductService productService;

    /**
     * Cette fonction affiche la liste des produits
     * @param model
     * @return la page dédiée aux produits
     */
    @GetMapping("/")
    public String home(Model model) {

        List<Product> products = productService.productList();

        model.addAttribute("products", products);

        log.info("Call list products page");

        return "home";
    }
}
