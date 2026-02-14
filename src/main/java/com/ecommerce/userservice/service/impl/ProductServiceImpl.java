package com.ecommerce.userservice.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.ecommerce.userservice.entity.Product;
import com.ecommerce.userservice.repository.AddressRepository;
import com.ecommerce.userservice.repository.ProductRepository;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private  ProductRepository productRepository;

 // Constructor Injection
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    
    @Override
    public Product addProduct(Product product, String adminEmail) {
        product.setCreatedBy(adminEmail);
        return productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findByActiveTrue();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }
}
