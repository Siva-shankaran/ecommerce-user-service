package com.ecommerce.userservice.service;

import java.util.List;

import com.ecommerce.userservice.entity.Order;

public interface OrderService {

    Order placeOrder(String email);
    List<Order> getUserOrders(String email);
}

