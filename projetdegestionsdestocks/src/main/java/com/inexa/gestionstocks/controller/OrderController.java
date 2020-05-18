package com.inexa.gestionstocks.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import com.inexa.gestionstocks.Form.OrderForm;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.model.Order;
import com.inexa.gestionstocks.model.Product;
import com.inexa.gestionstocks.service.CustomerService;
import com.inexa.gestionstocks.service.OrderService;
import com.inexa.gestionstocks.service.OrderServiceInterface;
import com.inexa.gestionstocks.service.ProductService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class OrderController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService defaultService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderServiceInterface orderService;

    /**
     * Cette fonction affiche la liste des commandes d'un client
     * @param id du client
     * @param model
     * @return la page dédiée aux commandes du client
     */
    @GetMapping("/customer-order/{id}")
    public String showCustomerOrderList(@PathVariable("id") long id, Model model) {

        log.info("Fonction d'affichage des commandes d'un client");

        Customer customer = defaultService.findById(id);

        model.addAttribute("customer", customer);

        return "orders/order";
    }

    /**
     * Cette fonction affiche la page de création de commande
     * @param id du client
     * @param orderForm contient les informations à enregistré dans le formulaire
     * @param model
     * @return la page de création des commandes
     */
    @GetMapping("/add-order/{id}")
    public String showOrderForm(@PathVariable("id") long id, OrderForm orderForm, Model model) {
        List<Product> products = productService.productList();

        model.addAttribute("customerId", id);
        model.addAttribute("products", products);

        return "orders/addOrder";
    }

    /**
     * Cette fonction valide et enregistre les commandes du client
     * @param id du client
     * @param orderForm contient les informations à enregistré dans le formulaire
     * @param bindingResult retourne le formulaire en cas du non respect des règles de validation
     * @param model
     * @param redirectAttributes
     * @return la page dédiée aux commandes d'un client
     */
    @PostMapping("/add-order/{id}")
    public String sendingForm(@PathVariable("id") long id, @Valid OrderForm orderForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
    {
        log.info("Fonction de validation du formulaire et d'envoie de mail");

        if (bindingResult.hasErrors()) {
            log.info("Retour sur le formulaire avec des messages d'erreurs");

            List<Product> products = productService.productList();
        
            model.addAttribute("customerId", id);
            model.addAttribute("products", products);

            return "orders/addOrder";
        }

        Customer customer = defaultService.findById(id);

        try {

            log.info("Debut de l'enregistrement");

            Product product = productService.findById(orderForm.getProductId());

            Order order = new Order();

            String orderNumber = order.getOrderNumberCode()+ (orderService.orderList().size() + 1);

            order.setOrderNumber(orderNumber);
            order.setOrderDate(LocalDateTime.now());
            order.setQuantity(orderForm.getQuantity());
            order.setStatus(1);
            order.setProduct(product);
            order.setCustomer(customer);

            orderService.addOrder(order);

            log.info("Fin de l'enregistrement");

        } catch (Exception e) {
            e.printStackTrace();
            log.error("Impossible d'enregistrer une commande", e);
        }

        redirectAttributes.addFlashAttribute("addSuccess", "true");

        return "redirect:/customer-order/"+id;
    }

}