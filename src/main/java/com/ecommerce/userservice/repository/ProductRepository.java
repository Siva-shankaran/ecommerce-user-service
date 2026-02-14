package com.ecommerce.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    List<Product> findByActiveTrue();
}
