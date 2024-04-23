package com.example.demo.controller;

import com.example.demo.dto.*;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.service.CartService;
import com.example.demo.service.CustomerService;
import com.example.demo.service.OrderService;
import com.example.demo.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class controller {
    private final ProductService productService;
    private final CustomerService customerService;
    private final CartService cartService;
    private final OrderService orderService;

    public controller(ProductService productService, CustomerService customerService, CartService cartService, OrderService orderService) {
        this.productService = productService;
        this.customerService = customerService;
        this.cartService = cartService;
        this.orderService = orderService;
    }

    @PostMapping("/addCustomer")
    public ResponseEntity<String> addCustomer(@RequestBody CustomerDto customer){
        if(customerService.addCustomer(customer)) {
            return ResponseEntity.ok("Customer added successfully");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @PostMapping("/addProduct")
    public ResponseEntity<String> addProduct(@RequestBody ProductDto product){
        if(productService.addProduct(product)) {
            return ResponseEntity.ok("Product added successfully");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @PatchMapping("/{productId}/updateProduct")
    public ResponseEntity<String> updateProduct(@PathVariable Long productId,@RequestBody ProductUpdateDto product){
        if(productService.updateProduct(productId,product)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updated Product");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error UpdateProduct");
        }

    @PostMapping("/deleteProduct")
    public ResponseEntity<Boolean> deleteProduct(@RequestBody DeleteDto deleteDto) {
        System.out.println(deleteDto.getId());
        boolean delete = productService.deleteProduct(deleteDto);

        if (delete==true) {
            // Transfer başarılıysa
            return ResponseEntity.status(HttpStatus.OK).body(delete);
        } else {
            // Transfer başarısızsa veya bir hata oluştuysa
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(delete);
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = productService.getProducts();
        if (products.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(products);
        }
    }
    @GetMapping("/getCustomers")
    public ResponseEntity<List<Customer>> getCustomers(){
        List<Customer> customers = customerService.getCustomers();
        if (customers.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(customers);
        }
    }
    @GetMapping("/getCart")
    public ResponseEntity<Cart> getCart(@RequestBody Customer customer){
            return ResponseEntity.ok(cartService.getCart(customer));
    }
    @PatchMapping("/updateCart")
    public ResponseEntity<String> updateCart(@RequestBody UpdateCartDto updateCartDto){
        if(cartService.updateCart(updateCartDto)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Updated Cart");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error updateCart");
    }
    @PatchMapping("/emptyCart")
    public ResponseEntity<String> updateCart(@RequestBody Customer customer){
        if(cartService.emptyCart(customer)){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart clear");
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error clear");
    }

    @PostMapping("/placeOrder")
    public ResponseEntity<String> placeOrder(@RequestBody OrderDto order){
        if(orderService.placeOrder(order)) {
            return ResponseEntity.ok("PlaceOrder successful");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @GetMapping("{orderId}/getOrderForCode")
    public ResponseEntity<Cart> getOrderForCode(@PathVariable Long orderId){
        Cart o = orderService.getOrderForCode(orderId);
        return ResponseEntity.ok(o);
    }

    @PostMapping("/addCart")
    public ResponseEntity<String> addCart(@RequestBody CartDto cartDto){
        if(cartService.addCart(cartDto)) {
            return ResponseEntity.ok("Product added successfully to Cart");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    @PostMapping("/{productId}/removeProductFromCart")
    public ResponseEntity<String> removeProductFromCart(@PathVariable Long productId,@RequestBody CartDto cartDto){
        if(cartService.removeProductFromCart(productId,cartDto)) {
            return ResponseEntity.ok("Product removed successfully to Cart");
        } else {
            return ResponseEntity.ok("Error");
        }
    }

    }

