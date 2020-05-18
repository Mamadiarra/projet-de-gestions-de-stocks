package com.inexa.gestionstocks.service;

import com.inexa.gestionstocks.model.Order;
import com.inexa.gestionstocks.repository.OrderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements OrderServiceInterface{

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private EmailService emailService;

    @Override
    public List<Order> orderList() {
        return orderRepository.findAll();
    }

    @Override
    public void addOrder (Order order)
    {
        orderRepository.save(order);
    }

    @Override
    //@Scheduled(cron = "0 0/1 * * * *")
    public void orderSheduleTask()
    {
        List<Order> orders = orderRepository.findAllByStatus(1);

        orders.forEach(order -> {
            // Send email
            emailService.sendSimpleMessage(
                    order.getCustomer().getEmail(),
                    "Rappel pour validation de la commande "+order.getOrderNumber(),
                    "Monsieur "+order.getCustomer().getName()+", vous recevez ce message car vous n'avez pas encore validé votre commande."
            );

        });
    }

}