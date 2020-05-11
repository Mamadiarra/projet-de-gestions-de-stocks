package com.inexa.gestionstocks.Form;

import javax.validation.constraints.NotNull;

public class OrderForm {
    
    @NotNull
    private Long productId;

    @NotNull
    private Long quantity;

    public Long getProductId() {
        return productId;
    }

    public Long setProductId(Long productId)
    {
        return this.productId = productId;
    }

    public Long getQuantity() {
        return quantity;
    }

    public Long setQuantity(Long quantity)
    {
        return this.quantity = quantity;
    }

}