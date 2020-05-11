package com.inexa.gestionstocks.controller;

import com.inexa.gestionstocks.Form.AddressForm;
import com.inexa.gestionstocks.model.Address;
import com.inexa.gestionstocks.model.Customer;
import com.inexa.gestionstocks.service.AddressService;
import com.inexa.gestionstocks.service.CustomerService;
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

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Controller
public class AddressController {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AddressService addressService;

    @Autowired
    private CustomerService defaultService;

    /**
     *
     * @param id du client
     * @param model
     * @return deux objets (customer et les adresses du clients)
     */
    @GetMapping("/customer-address/{id}")
    public String customerAddress(@PathVariable("id") long id, Model model)
    {
        log.info("Call customer address page");

        Customer customer = defaultService.findById(id);

        List<Address> addresses = addressService.listAddressByCustomer(customer);

        model.addAttribute("customer", customer);
        model.addAttribute("addresses", addresses);

        return "addressList";
    }

    /**
     *
     * @param id du client
     * @param addressForm l'objet contenant les champs de address
     * @param model
     * @return le formulaire d'ajout d'adresse et les informations du client
     */
    @GetMapping("/add-address/{id}")
    public String showAddressForm(@PathVariable("id") long id, AddressForm addressForm, Model model) {
        log.info("Call address form page");

        Customer customer = defaultService.findById(id);

        model.addAttribute("customer", customer);

        return "addressForm";
    }

    /**
     *
     * @param id du client
     * @param addressForm l'objet contenant les champs de address
     * @param bindingResult
     * @param model
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/add-address/{id}")
    public String storeAddress(@PathVariable("id") long id, @Valid AddressForm addressForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {
        log.info("Call store address function");

        Customer customer = defaultService.findById(id);

        if (bindingResult.hasErrors()) {
            log.info("Show form  with error message");

            model.addAttribute("customer", customer);

            return "addressForm";
        }

        try {

            log.info("Starting Store address");

            Address address = new Address();
    
            address.setAddressCity(addressForm.getAddressCity());
    
            address.setAddressLocation(addressForm.getAddressLocation());
    
            address.setAddressName(addressForm.getAddressName());
    
            address.setCustomer(customer);
    
            addressService.addAddress(address);

            log.info("End Store address");
            
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Impossible to store address", e);
        }

        redirectAttributes.addFlashAttribute("success", "true");

        return "redirect:/customer-address/" + id;
    }

    /**
     *
     * @param id de l'adresse à supprimer
     * @param model
     * @param redirectAttributes
     * @return la liste des adresses d'un client
     */
    @GetMapping("/delete-address/{id}")
    public String deleteAddress(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes)
    {
        log.info("Call delete address id " + id);

        Optional<Address> address = addressService.findById(id);

        try {
            addressService.deleteById(id);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("Impossible to delete address", e);
        }

        redirectAttributes.addFlashAttribute("deleteSuccess", "true");

        return "redirect:/customer-address/" + address.get().getCustomer().getId();
    }

}
