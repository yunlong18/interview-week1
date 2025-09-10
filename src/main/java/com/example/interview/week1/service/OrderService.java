package com.example.interview.week1.service;

import com.example.interview.week1.entity.Order;

public interface OrderService {
    
    Order getById(String id);

    String create(Order order);
}
