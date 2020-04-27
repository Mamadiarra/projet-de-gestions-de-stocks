package com.inexa.gestionstocks.Form;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

@Controller
public class WebController implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/productresults").setViewName("productresults");
    }

    @GetMapping("/add-product")
    public String showForm(ProductForm productForm) {
        log.info("Call add product form page");
        return "myproduct";
    }

    @PostMapping("/add-product")
    public String checkProductInfo(@Valid ProductForm productForm, BindingResult bindingResult) {
        log.info("Check product information method");

        if (bindingResult.hasErrors()) {
            return "myproduct";
        }

        return "redirect:/productresults";
    }

}
