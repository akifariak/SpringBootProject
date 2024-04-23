package com.example.demo.service;

import com.example.demo.dto.DeleteDto;
import com.example.demo.dto.ProductDto;
import com.example.demo.dto.ProductUpdateDto;
import com.example.demo.model.Cart;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;


    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public boolean addProduct(ProductDto product){
        Product p = new Product(product.getProductName(),product.getAmount(),product.getPrice());
        productRepository.save(p);
        return true;
    }

    public boolean updateProduct(Long productId,ProductUpdateDto productUpdateDto){
        Product p = productRepository.findProductByProductId(productId);
        System.out.println(p);
        p.setProductName(productUpdateDto.getProductName());
        p.setAmount(productUpdateDto.getAmount());
        p.setPrice(productUpdateDto.getPrice());
      productRepository.save(p);
        return true;
    }

    public boolean deleteProduct(DeleteDto deleteDto){
        Product p = productRepository.findProductByProductId(deleteDto.getId());
        productRepository.deleteById(p.getProductId());
        return true;
    }

    public List<Product> getProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(Long productId){
        return productRepository.findProductByProductId(productId);
    }
}
