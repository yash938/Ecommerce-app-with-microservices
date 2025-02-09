package com.microservices.dto;

import com.microservices.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDto {
    private Order order;
    private PaymentResponseDto paymentResponseDto;
    private UserDto userDto;
}
