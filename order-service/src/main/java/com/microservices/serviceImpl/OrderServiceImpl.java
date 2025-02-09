package com.microservices.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.dto.OrderResponseDto;
import com.microservices.dto.PaymentResponseDto;
import com.microservices.dto.UserDto;
import com.microservices.entity.Order;
import com.microservices.repository.OrderRepo;
import com.microservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private OrderRepo orderRepo;

    @Value("${order.producer.topic.name}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String ,Object> kafkaTemplate;

    @Override
    public String placeOrder(Order order) throws JsonProcessingException {
        order.setPurchaseDate(new Date());
        order.setOrderId(UUID.randomUUID().toString());
        orderRepo.save(order);
        kafkaTemplate.send(topicName,new ObjectMapper().writeValueAsString(order));
        return "your order with (" +order.getOrderId()+ ") has been placed we will notify it will confirmed ";
    }

    public OrderResponseDto getOrder(String orderId){
        Order order  = orderRepo.findByOrderId(orderId);

        PaymentResponseDto payment = restTemplate.getForObject("http://PAYMENT-SERVICE:9094/payment" + "/" + order.getOrderId(), PaymentResponseDto.class);
        UserDto user = restTemplate.getForObject("http://USER-SERVICE:9094/user/" + order.getUserId(), UserDto.class);



        return OrderResponseDto.builder()
                .order(order)
                .paymentResponseDto(payment)
                .userDto(user)
                .build();
    }
}
