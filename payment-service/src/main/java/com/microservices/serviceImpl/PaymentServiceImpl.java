package com.microservices.serviceImpl;

import com.microservices.entity.Payment;
import com.microservices.repository.PaymentRepo;
import com.microservices.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo;
    @Override
    public Payment getOrderById(String orderId) {
        return paymentRepo.findByOrderId(orderId);
    }
}
