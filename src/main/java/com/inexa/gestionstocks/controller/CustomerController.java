package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.Form.CustomerForm;
import com.inexa.gestionstocks.Form.SendingForm;

import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.CustomerService;
import com.inexa.gestionstocks.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@Controller
public class CustomerController implements WebMvcConfigurer {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService defaultService;

    @Autowired
    private EmailService emailService;
    /*
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/customers").setViewName("customers");
    }
    */

    /**
     * Cette méthode affiche la liste des clients
     * @param model
     * @return la liste des clients;
     */
    @GetMapping("/customers")
    public String listCustomer(HttpServletRequest request, Model model)
    {
        model.addAttribute("customersForDatatable", defaultService.listCustomerForClientDatatable());

        int page = 0;
        int size = 5;

        if (request.getParameter("page") != null && !request.getParameter("page").isEmpty()) {
            page = Integer.parseInt(request.getParameter("page")) - 1;
        }

        if (request.getParameter("size") != null && !request.getParameter("size").isEmpty()) {
            size = Integer.parseInt(request.getParameter("size"));
        }

        model.addAttribute("customers", defaultService.listCustomerWithPagination(PageRequest.of(page, size)));

        return "customers";
    }

    /**
     * Cette méthode affiche un formulaire d'ajout de client
     * @param customerForm retourne un objet contenant les informations du client à enregistré dans le formulaire
     * @param principal affiche les informations de l'utilisateur connecté
     * @return un formulaire d'ajout des clients
     */
    @GetMapping("/add-customer")
    public String showForm(CustomerForm customerForm, Principal principal) {
        log.debug("L'utilisateur connecté est :" + principal.getName());
        return "addcustomer";
    }

    /**
     * Cette méthode valide et enregistre un client en base de données
     * @param customerForm retourne un objet contenant les informations du client à enregistré dans le formulaire
     * @param bindingResult
     * @return
     */
    @PostMapping("/add-customer")
    public String storeCustomerInfo(@Valid CustomerForm customerForm, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "addcustomer";
        }

        Customer customer = new Customer();

        customer.setName(customerForm.getName());

        customer.setEmail(customerForm.getEmail());

        customer.setPhone(customerForm.getPhone());

        customer.setLocation(customerForm.getLocation());

        defaultService.addCustomer(customer);

        return "redirect:/customers";
    }

    /**
     * Cette méthode supprime un client en base de données
     * @param id est l'identifiant du client à supprimer
     * @param model
     * @return la liste des clients qui sont en base de données
     */
    @GetMapping("/delete-customer/{id}")
    public String deleteCustomer(@PathVariable("id") long id, Model model)
    {
        defaultService.deleteById(id);

        return "redirect:/customers";
    }

    /**
     * Cette méthode affiche le formulaire de recherche par nom des clients.
     * @return un formulaire de recherche de clients par nom
     */
    @GetMapping("/search-customer")
    public String searchCustomer() {

        return "searchForm";
    }

    /**
     * Cette méthode valide le formulaire de recherche et affiche le resultat
     * @param name correspond à la valeur du champs à saisir
     * @param model
     * @return la liste des clients trouvés
     */
    @PostMapping("/search-customer")
    public String searchCustomer(String name, Model model) {

        List<Customer> customers = defaultService.findByName(name);

        model.addAttribute("customers", customers);

        return "searchResults";
    }

    /**
     *
     * @param name fait référence au nom du client
     * @param phone fait référence au contact du client
     * @param email fait référence à l'email du client
     * @param location fait référence à la localisation du client
     * @param model
     * @return une page de resultat avec la liste des clients trouvés
     */
    @PostMapping("/search-customer-field")
    public String searchCustomerWithMultiplesFields(@RequestParam(required = false) String name,@RequestParam(required = false) String phone,@RequestParam(required = false) String email,@RequestParam(required = false) String location, Model model) {

        log.info("Search with multiple field");

        List<Customer> customers = defaultService.findAllMultiplesFields(name, phone, email, location);

        model.addAttribute("customers", customers);

        return "searchResults";
    }

    @GetMapping("/send-email-customer/{id}")
    public String showSendingForm(@PathVariable("id") long id, SendingForm sendingForm, Model model)
    {
        model.addAttribute("customerId", id);

        return "showSendingForm";
    }
    
    @PostMapping("/send-email-customer/{id}")
    public String sendingForm(@PathVariable("id") long id, @Valid SendingForm sendingForm, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "showSendingForm";
        }

        Customer customer = defaultService.findById(id);

        emailService.sendSimpleMessage(customer.getEmail(), sendingForm.getSubject(), sendingForm.getDescription());

        return "redirect:/customers";
    }
}
