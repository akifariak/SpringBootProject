package com.example.demo.service;

import com.example.demo.dto.CartDto;
import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.dto.UpdateCartDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Customer;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.repository.CartRepository;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    public CartService(CustomerRepository customerRepository, CartRepository cartRepository, CustomerRepository customerRepository1, ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.customerRepository = customerRepository1;
        this.productRepository = productRepository;
    }

    public boolean createCart(Customer customer){
        Cart c = new Cart(customer);
        cartRepository.save(c);
        return true;
    }
    public Cart getCart(Customer customer){
        return cartRepository.findCartByCustomer(customer);
    }

    public boolean addCart(CartDto cartDto){

        Cart c = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(cartDto.getCustomerId()));
        System.out.println(c);
        List<Product> productList =c.getProduct();
        productList.add(  productRepository.findProductByProductId(cartDto.getProductId()));
        c.setProduct(productList);
        cartRepository.save(c);
        return true;
    }
    public boolean removeProductFromCart(Long productId, CartDto cartDto){
        Cart c = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(cartDto.getCustomerId()));
        List<Product> productList =c.getProduct();
        productList.remove(productRepository.findProductByProductId(cartDto.getProductId()));
        c.setProduct(productList);
        cartRepository.save(c);
        return true;
    }

//    public Order getOrderForCode(Long productId, CartDto cartDto){
//        Cart cart = cartRepository.findCartByCustomer(customerRepository.findCustomerByCustomerId(cartDto.getCustomerId()));
//        List<Product> productList = cart.getProduct();
//
//        Product product = productList.stream()
//                .filter(p -> p.getProductId().equals(productId))
//                .findFirst()
//                .orElse(null);
//
//        return product;
//    }

    public boolean updateCart(UpdateCartDto updateCartDto){
        Cart c = cartRepository.findCartByCartId(updateCartDto.getCartId());
        System.out.println(c);
        List<Product> productList= c.getProduct();
        System.out.println(productList);
        System.out.println("updateCart"+updateCartDto.getProducts());
        productList=updateCartDto.getProducts();
        System.out.println("3"+productList);
        c.setProduct(productList);
        cartRepository.save(c);
        return true;
    }

    public boolean emptyCart(Customer customer){
        Cart c = cartRepository.findCartByCustomer(customer);
        List<Product> productList= c.getProduct();
        productList.clear();
        c.setProduct(productList);
        cartRepository.save(c);
        return true;
    }



}
