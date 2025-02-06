package com.microservices.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.entity.Order;

public interface OrderService {

    String placeOrder(Order order) throws JsonProcessingException;

    String getOrder(String orderId);
}
