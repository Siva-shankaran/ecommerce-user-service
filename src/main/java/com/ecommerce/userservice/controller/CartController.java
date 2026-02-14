package com.ecommerce.userservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.entity.Cart;
import com.ecommerce.userservice.service.CartService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public String addToCart(
            @RequestParam Long productId,
            @RequestParam Integer qty,
            Authentication auth) {

        cartService.addToCart(auth.getName(), productId, qty);
        return "Added to cart";
    }

    @GetMapping
    public Cart getCart(Authentication auth) {
        return cartService.getCart(auth.getName());
    }

    @DeleteMapping("/remove/{productId}")
    public String remove(
            @PathVariable Long productId,
            Authentication auth) {

        cartService.removeItem(auth.getName(), productId);
        return "Item removed";
    }
}
