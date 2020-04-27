package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.Form.Product;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

    @GetMapping("/add-product-handling")
    public String productHandlingForm(Model model)
    {
        model.addAttribute("product", new Product());
        return "myhandlingproduct";
    }

    @PostMapping("/add-product-handling")
    public String greetingSubmit(@ModelAttribute Product product) {
        return "handlingresult";
    }

}