package com.example.demo.service;

import com.example.demo.dto.CustomerDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Product;
import com.example.demo.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    public final CustomerRepository customerRepository;
    private final CartService cartService;

    public CustomerService(CustomerRepository customerRepository, CartService cartService) {
        this.customerRepository = customerRepository;
        this.cartService = cartService;
    }

    public boolean addCustomer(CustomerDto customer){
        Customer c = new Customer(customer.getName(),customer.getSurname());
        customerRepository.save(c);
        cartService.createCart(c);

        return true;
    }

    public List<Customer> getCustomers(){
        return customerRepository.findAll();
    }
}
