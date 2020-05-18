package com.inexa.gestionstocks.Form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductForm {

    @NotNull
    private String numberProduct;

    @NotNull
    private String name;

    @NotNull
    @Min(3)
    private Integer price;

    @NotNull
    private String description;

    public String getNumberProduct() {
        return numberProduct;
    }

    public void setNumberProduct(String numberProduct) {
        this.numberProduct = numberProduct;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
