package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Order;

import java.util.List;

public interface OrderServiceInterface {

    public List<Order> orderList();
    
    public void addOrder (Order order);

    public void orderSheduleTask();

}