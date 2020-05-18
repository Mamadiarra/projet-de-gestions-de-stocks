package com.inexa.gestionstocks.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException() {
        super("Customer id not found : ");
    }
}
