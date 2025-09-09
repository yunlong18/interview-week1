package com.example.interview.week1.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.interview.week1.dto.OrderDTO;
import com.example.interview.week1.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

    private List<OrderDTO> orders = List.of(
            new OrderDTO("1", "Laptop", 2),
            new OrderDTO("2", "Phone", 5));

    @Override
    public OrderDTO getById(String id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
