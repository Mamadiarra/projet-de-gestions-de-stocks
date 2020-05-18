package com.inexa.gestionstocksrestapi.Tools;

import com.inexa.gestionstocksrestapi.model.Customer;
import org.springframework.stereotype.Service;

@Service
public class ApiTools {

    public Customer mergeObject(Customer currentData, Customer newData)
    {
        if (newData.getName() == null) {
            newData.setName(currentData.getName());
        }

        if (newData.getPhone() == null) {
            newData.setPhone(currentData.getPhone());
        }

        if (newData.getEmail() == null) {
            newData.setEmail(currentData.getEmail());
        }

        if (newData.getLocation() == null) {
            newData.setLocation(currentData.getLocation());
        }

        return newData;
    }

}
