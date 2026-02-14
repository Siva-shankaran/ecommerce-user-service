package com.ecommerce.userservice.dto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUserEmail(String email);
}
