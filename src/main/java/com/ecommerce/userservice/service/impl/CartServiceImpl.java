package com.ecommerce.userservice.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.userservice.dto.ProductDto;
import com.ecommerce.userservice.entity.Cart;
import com.ecommerce.userservice.entity.CartItem;
import com.ecommerce.userservice.repository.CartRepository;
import com.ecommerce.userservice.repository.ProductRepository;
import com.ecommerce.userservice.service.CartService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

	private CartRepository cartRepository;
    private RestTemplate restTemplate;

    public CartServiceImpl(CartRepository cartRepository,
                           RestTemplate restTemplate) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
    }
    
    private static final String PRODUCT_SERVICE_URL =
            "http://localhost:8082/api/products/";

    @Override
    public void addToCart(String email, Long productId, Integer qty) {
    	
        Cart cart = cartRepository.findByUserEmail(email)
                .orElseGet(() -> {
                    Cart c = new Cart();
                    c.setUserEmail(email);
                    return c;
                });

        ProductDto product = restTemplate.getForObject(
                PRODUCT_SERVICE_URL + productId,
                ProductDto.class
        );

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        CartItem item = cart.getItems().stream()
                .filter(i -> i.getProductId().equals(productId))
                .findFirst()
                .orElse(null);

        if (item == null) {
            item = new CartItem();
            item.setProductId(product.getId());
            item.setProductName(product.getName());
            item.setPrice(product.getPrice());
            item.setQuantity(qty);
            item.setCart(cart);
            cart.getItems().add(item);
        } else {
            item.setQuantity(item.getQuantity() + qty);
        }

        cart.setTotalPrice(
                cart.getItems().stream()
                        .mapToDouble(i -> i.getPrice() * i.getQuantity())
                        .sum()
        );

        cartRepository.save(cart);
    }

	@Override
	public Cart getCart(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeItem(String email, Long productId) {
		// TODO Auto-generated method stub
		
	}
}
