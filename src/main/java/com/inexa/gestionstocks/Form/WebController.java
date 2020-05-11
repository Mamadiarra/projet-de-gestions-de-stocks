package com.inexa.gestionstocks.Form;

import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.service.ProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.validation.Valid;

/**
 * WebController est la classe qui permet de valider un formulaire
 */

@Controller
public class WebController implements WebMvcConfigurer {

    private final ProductService productService;

    // Voici comment instancier un Logger
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public WebController(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/productresults").setViewName("productresults");
    }

    /**
     * ShowForm permet d'afficher le formulaire d'ajout de produit
     * @param productForm: represente le formulaire qui contient (le nom, le numéro, le prix et la description)
     * @return le lien vers la page de création des produits
     */
    @GetMapping("/add-product")
    public String showForm(ProductForm productForm) {
        log.info("Call add product form page");
        return "myproduct";
    }

    @PostMapping("/add-product")
    public String checkProductInfo(@Valid ProductForm productForm, @RequestParam("fileImage") MultipartFile fileImage, BindingResult bindingResult) {
        log.info("Check product information method");

        if (bindingResult.hasErrors()) {
            return "myproduct";
        }

        String fileName = null;

        try {
            fileName = productService.storeFileOnDisk(fileImage);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("error to save image", e);
        }

        Product product = new Product();

        product.setNumberProduct(productForm.getNumberProduct());

        product.setName(productForm.getName());

        product.setPrice(productForm.getPrice());

        product.setDescription(productForm.getDescription());

        product.setFileName(fileName);

        productService.addProduct(product);

        return "redirect:/productresults";

    }

}
