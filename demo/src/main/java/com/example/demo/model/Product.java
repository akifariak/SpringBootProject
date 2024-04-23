package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Data
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private Long productId;

    @Column(name = "productName", nullable = false)
    private String productName;

    @Column(name = "amount", nullable = false)
    private Integer amount;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    public Product(String productName, Integer amount, BigDecimal price){
        this.productName=productName;
        this.amount=amount;
        this.price=price;
    }

    public Product() {

    }
}
