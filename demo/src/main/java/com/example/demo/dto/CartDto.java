package com.example.demo.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
public class CartDto {
    private Long customerId;
    private String name;
    private String surname;
    private String productName;
    private Integer amount;
    private BigDecimal price;
    private Long productId;
}
