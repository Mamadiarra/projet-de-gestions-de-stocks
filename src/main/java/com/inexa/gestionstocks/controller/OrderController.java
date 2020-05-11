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

    @Autowired
    private CustomerService defaultService;

    @Autowired
    private ProductService productService;

    @Autowired
    private OrderServiceInterface orderService;

    @GetMapping("/customer-order/{id}")
    public String showCustomerOrderList(@PathVariable("id") long id, Model model) {
        Customer customer = defaultService.findById(id);

        model.addAttribute("customer", customer);

        return "orders/order";
    }

    @GetMapping("/add-order/{id}")
    public String showOrderForm(@PathVariable("id") long id, OrderForm orderForm, Model model) {
        List<Product> products = productService.productList();

        model.addAttribute("customerId", id);
        model.addAttribute("products", products);

        return "orders/addOrder";
    }

    @PostMapping("/add-order/{id}")
    public String sendingForm(@PathVariable("id") long id, @Valid OrderForm orderForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes)
    {
    
        if (bindingResult.hasErrors()) {
            List<Product> products = productService.productList();
        
            model.addAttribute("customerId", id);
            model.addAttribute("products", products);

            return "orders/addOrder";
        }

        Customer customer = defaultService.findById(id);
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

        redirectAttributes.addFlashAttribute("addSuccess", "true");

        return "redirect:/customer-order/"+id;
    }

}