package com.inexa.gestionstocksihm.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.inexa.gestionstocksihm.form.CustomerForm;
import com.inexa.gestionstocksihm.model.Customer;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.validation.BindingResult;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
public class CustomerController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/list-customer")
    public String listCustomer(Model model) {
        List<CustomerForm> customers = callApi();

        model.addAttribute("customers", customers);

        return "listCustomer";
    }

    @GetMapping("/add-customer")
    public String addCustomer(final CustomerForm customerForm) {
        return "addCustomer";
    }

    @PostMapping("/add-customer")
    public String storeCustomer(@Valid CustomerForm customerForm, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "addCustomer";
        }

        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:9090/add-customer";

        Customer customer = new Customer();

        customer.setName(customerForm.getName());
        customer.setPhone(customerForm.getPhone());
        customer.setEmail(customerForm.getEmail());
        customer.setLocation(customerForm.getLocation());

        HttpEntity<Customer> request = new HttpEntity<>(customer);

        ResponseEntity<Customer> response = restTemplate.exchange(resourceUrl, HttpMethod.POST, request,
            Customer.class);
        
        if (response.getStatusCode() != HttpStatus.OK) {
            redirectAttributes.addFlashAttribute("error", "true");
            return "addCustomer";
        }

        redirectAttributes.addFlashAttribute("success", "true");
        
        return "redirect:/list-customer";
    }

    @GetMapping("/update-customer/{id}")
    public String addCustomer(@PathVariable long id, CustomerForm customerForm, Model model) {

        RestTemplate restTemplate = new RestTemplate();
        String getUrl = "http://localhost:9090/get-customer/"+ id;
        
        ResponseEntity<Customer> response = restTemplate.exchange(getUrl, HttpMethod.GET, null, Customer.class);

        if (response.getStatusCode() == HttpStatus.OK)
        {
            Customer customer = response.getBody();

            customerForm.setName(customer.getName());
            customerForm.setPhone(customer.getPhone());
            customerForm.setEmail(customer.getEmail());
            customerForm.setLocation(customer.getLocation());
        }

        model.addAttribute("id", id);

        return "updateCustomer";
    }

    @PostMapping("/update-customer/{id}")
    public String updateCustomer(@PathVariable long id, @Valid CustomerForm customerForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "updateCustomer";
        }

        
        RestTemplate restTemplate = new RestTemplate();

        String resourceUrl = "http://localhost:9090/update-customer/"+ id;

        Customer customer = new Customer();

        customer.setName(customerForm.getName());
        customer.setPhone(customerForm.getPhone());
        customer.setEmail(customerForm.getEmail());
        customer.setLocation(customerForm.getLocation());

        HttpEntity<Customer> request = new HttpEntity<>(customer);

        ResponseEntity<Customer> response = restTemplate.exchange(resourceUrl, HttpMethod.PUT, request,Customer.class);
        
        

        return "redirect:/list-customer";
    }

    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable long id)
    {	
        RestTemplate restTemplate = new RestTemplate();
        String getUrl = "http://localhost:9090/delete-customer/"+ id;
        
        restTemplate.exchange(getUrl, HttpMethod.DELETE, null, Customer.class);

        return "redirect:/list-customer";
    }

    private List<CustomerForm> callApi()
    {
        List<CustomerForm> customers =  new ArrayList<CustomerForm>();

        final RestTemplate restTemplate = new RestTemplate();
        
        final String resourceUrl = "http://localhost:9090/list-customer";

        // Add the Jackson message converter
        restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
        
        // Make the HTTP GET request, marshaling the response from JSON to an array of Events
        final CustomerForm[] customerFormTable = restTemplate.getForObject(resourceUrl, CustomerForm[].class);
        
        customers = Arrays.asList(customerFormTable);

        return customers;
    }

}