package com.example.demo.service;

import com.example.demo.dto.OrderDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.CartRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;

    public OrderService(OrderRepository orderRepository, CartRepository cartRepository, CustomerRepository customerRepository) {
        this.orderRepository = orderRepository;
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository;
    }

    public boolean placeOrder(OrderDto orderDto){
        Cart c = cartRepository.findCartByCartId(orderDto.getCartId());
        Order o = new Order(c.getCartId(),orderDto.getRegistrationDate());
        orderRepository.save(o);
        c.getProduct().clear();
        cartRepository.save(c);
        return true;
    }

    public Cart getOrderForCode(Long orderId){
        Order o = orderRepository.findOrderByOrderId(orderId);
        Cart c = cartRepository.findCartByCartId(o.getCartId());
        o.setCart(c);
        System.out.println(o);
        return o.getCart();
    }

    public Order getAllOrdersForCustomer(Long customerId) {
        Customer c = customerRepository.findCustomerByCustomerId(customerId);

        return null;
    }


}
