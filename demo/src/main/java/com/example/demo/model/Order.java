package com.example.demo.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Data
@Entity
@Table(name = "Porder")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;


    private Long cartId;

    @ManyToOne
    private Cart cart;

    @Column(name = "registrationDate" , nullable = false)
    private LocalDate registrationDate;

    public Order(Long cartId, LocalDate registrationDate){
        this.cartId=cartId;
        this.registrationDate=registrationDate;
    }

    public Order() {}
}
