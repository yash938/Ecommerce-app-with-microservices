package com.microservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.dto.OrderResponseDto;
import com.microservices.entity.Order;

public interface OrderService {

    String placeOrder(Order order) throws JsonProcessingException;

    OrderResponseDto getOrder(String orderId);
}
