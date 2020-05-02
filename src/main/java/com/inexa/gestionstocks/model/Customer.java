package com.inexa.gestionstocks.model;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
@Table(name="customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 5, max = 190)
    private String location;

    @OneToMany(mappedBy = "customer")
    private Set<Address> addresses;

    public Long getId()
    {
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
