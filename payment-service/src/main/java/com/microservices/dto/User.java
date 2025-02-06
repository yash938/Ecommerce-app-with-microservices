package com.microservices.dto;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class User {

    private int userId;
    private String name;
    private String email;
    private double availableAmount;
}
