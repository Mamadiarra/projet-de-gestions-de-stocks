package com.inexa.gestionstocks.Form;

import javax.validation.constraints.NotNull;

public class AddressForm {

    @NotNull
    private String addressName;

    @NotNull
    private String addressCity;

    @NotNull
    private String addressLocation;

    public String getAddressName() {
        return addressName;
    }

    public void setAddressName(String addressName) {
        this.addressName = addressName;
    }

    public String getAddressCity() {
        return addressCity;
    }

    public void setAddressCity(String addressCity) {
        this.addressCity = addressCity;
    }

    public String getAddressLocation() {
        return addressLocation;
    }

    public void setAddressLocation(String addressLocation) {
        this.addressLocation = addressLocation;
    }
}
