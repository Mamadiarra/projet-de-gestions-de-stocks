package com.inexa.gestionstocksihm.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class CustomerForm {

    private Long id;
    
    @NotNull
    @Size(min=5, max=190)
    private String name;

    @NotNull
    @Size(min = 8, max = 13)
    private String phone;

    @NotNull
    @Email
    @Size(min = 10, max = 190)
    private String email;

    @NotNull
    @Size(min = 5, max = 190)
    private String location;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}