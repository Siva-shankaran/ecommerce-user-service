package com.ecommerce.userservice.controller;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.userservice.dto.OrderRepository;
import com.ecommerce.userservice.entity.Order;
import com.ecommerce.userservice.repository.CartRepository;
import com.ecommerce.userservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private OrderService orderService;
    
    public OrderController(OrderService orderService) {
    this.orderService = orderService;
}

    @PostMapping("/place")
    public Order placeOrder(Authentication auth) {
        return orderService.placeOrder(auth.getName());
    }

    @GetMapping
    public List<Order> getOrders(Authentication auth) {
        return orderService.getUserOrders(auth.getName());
    }
}
