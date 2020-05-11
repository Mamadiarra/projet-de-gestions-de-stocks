package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.Form.ProductBean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {
    /**
     * Cette fonction affiche la page dédiée aux produits
     * @param model
     * @return la page des produits
     */
    @GetMapping("/add-product-handling")
    public String productHandlingForm(Model model)
    {
        model.addAttribute("product", new ProductBean());
        return "myhandlingproduct";
    }

    @PostMapping("/add-product-handling")
    public String greetingSubmit(@ModelAttribute ProductBean product) {
        return "handlingresult";
    }

}
