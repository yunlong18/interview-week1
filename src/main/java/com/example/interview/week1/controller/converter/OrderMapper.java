package com.example.interview.week1.controller.converter;

import org.mapstruct.Mapper;

import com.example.interview.week1.dto.OrderDTO;
import com.example.interview.week1.entity.Order;

@Mapper
public interface OrderMapper {

    OrderDTO toOrderDTO(Order order);

    Order toOrder(OrderDTO orderDTO);
}
