package com.microservices.service;

import com.microservices.entity.Payment;

public interface PaymentService {
    Payment getOrderById(String orderId);
}
