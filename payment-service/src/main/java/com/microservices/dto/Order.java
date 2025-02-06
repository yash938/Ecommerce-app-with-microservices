package com.microservices.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@Data
@AllArgsConstructor

@Builder
public class Order {


    private  int id;
    private String name;
    private String category;
    private double price;
    private Date purchaseDate;
    private String orderId;
    private String paymentMode;
    private int userId;

    public Order() {
    }

}
