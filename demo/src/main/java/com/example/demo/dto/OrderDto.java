package com.example.demo.dto;

import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class OrderDto {
    private Long cartId;
    private LocalDate registrationDate;
}
