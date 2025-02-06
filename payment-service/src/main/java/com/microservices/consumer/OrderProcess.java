package com.microservices.consumer;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.dto.Order;
import com.microservices.dto.User;
import com.microservices.entity.Payment;
import com.microservices.repository.PaymentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

@Component
public class OrderProcess {
    @Autowired
    private PaymentRepo paymentRepo;

    private String URL= "http://localhost:9093/user";

    @Autowired
    private RestTemplate restTemplate;

    @KafkaListener(topics = "ORDER_PAYMENT",groupId = "payment_consumer_group")
    public void processOrder(String orderJsonString){



        try {
            Order order = new ObjectMapper().readValue(orderJsonString, Order.class);

            //build payment request
            Payment payment = Payment.builder().payMode(order.getPaymentMode())
                    .amount(order.getPrice())
                    .paidDate(new Date())
                    .userId(order.getUserId())
                    .orderId(order.getOrderId())
                    .build();

            if(payment.getPayMode().equals("COD")){
                payment.setPaymentStatus("PENDING");
            }
            else{
                User user = restTemplate.getForObject(URL +"/"+ payment.getUserId(), User.class);

                if(payment.getAmount()>user.getAvailableAmount()){
throw new RuntimeException("Insufficient amount");
                }else{
                payment.setPaymentStatus("PAID");

                restTemplate.put(URL+"/"+payment.getUserId()+"/"+payment.getAmount(),null);
                }
            }
            paymentRepo.save(payment);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
