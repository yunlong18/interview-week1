package com.example.interview.week1.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.interview.week1.entity.Order;
import com.example.interview.week1.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private List<Order> orders = new ArrayList<>(List.of(
            new Order("1", "Laptop", 2),
            new Order("2", "Phone", 5)));

    @Override
    public Order getById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String create(Order order) {
        order.setId(UUID.randomUUID().toString());
        orders.add(order);
        return order.getId();
    }
}
