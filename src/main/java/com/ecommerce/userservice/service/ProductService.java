package com.ecommerce.userservice.service;

import java.util.List;

import com.ecommerce.userservice.entity.Product;

public interface ProductService {

	Product addProduct(Product product, String adminEmail);
    List<Product> getAllProducts();
    Product getProductById(Long id);
    
}

