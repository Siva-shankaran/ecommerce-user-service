package com.ecommerce.userservice.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.ecommerce.userservice.dto.OrderRepository;
import com.ecommerce.userservice.entity.Cart;
import com.ecommerce.userservice.entity.CartItem;
import com.ecommerce.userservice.entity.Order;
import com.ecommerce.userservice.entity.OrderItem;
import com.ecommerce.userservice.repository.CartRepository;
import com.ecommerce.userservice.service.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

	
	private CartRepository cartRepository;
    private RestTemplate restTemplate;
    private  OrderRepository orderRepository;


    public OrderServiceImpl(CartRepository cartRepository,
                           RestTemplate restTemplate,OrderRepository orderRepository) {
        this.cartRepository = cartRepository;
        this.restTemplate = restTemplate;
        this.orderRepository = orderRepository;
    }
    
   

    private static final String PRODUCT_SERVICE_URL =
            "http://localhost:8082/api/products/";

    @Override
    public Order placeOrder(String email) {

        Cart cart = cartRepository.findByUserEmail(email)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        Order order = new Order();
        order.setUserEmail(email);
        order.setStatus("CREATED");

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cart.getItems()) {

            OrderItem item = new OrderItem();
            item.setProductId(cartItem.getProductId());
            item.setProductName(cartItem.getProductName());
            item.setPrice(cartItem.getPrice());
            item.setQuantity(cartItem.getQuantity());
            item.setOrder(order);

            orderItems.add(item);

            // 🔥 Reduce stock (call product service)
            restTemplate.put(
                    PRODUCT_SERVICE_URL + cartItem.getProductId() + "/reduce-stock?qty=" + cartItem.getQuantity(),
                    null
            );
        }

        order.setItems(orderItems);
        order.setTotalAmount(cart.getTotalPrice());

        Order savedOrder = orderRepository.save(order);

        // 🧹 Clear cart after order
        cart.getItems().clear();
        cart.setTotalPrice(0.0);
        cartRepository.save(cart);

        return savedOrder;
    }

    @Override
    public List<Order> getUserOrders(String email) {
        return orderRepository.findByUserEmail(email);
    }
}
