package com.ecommerce.userservice.service;

import com.ecommerce.userservice.entity.Cart;

public interface CartService {

    void addToCart(String email, Long productId, Integer qty);
    Cart getCart(String email);
    void removeItem(String email, Long productId);
}

