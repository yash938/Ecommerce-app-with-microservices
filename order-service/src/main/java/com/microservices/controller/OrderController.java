package com.microservices.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.microservices.entity.Order;
import com.microservices.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/takeOrder")
    public ResponseEntity<String> order(@RequestBody Order order) throws JsonProcessingException {
        String s = orderService.placeOrder(order);
        return new ResponseEntity<>(s, HttpStatus.OK);
    }
    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable String orderId){
        return orderService.getOrder(orderId);
    }
}
