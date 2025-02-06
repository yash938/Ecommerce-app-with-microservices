package com.microservices.serviceImpl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservices.entity.Order;
import com.microservices.repository.OrderRepo;
import com.microservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

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

    public String getOrder(String orderId){
        return null;
    }
}
