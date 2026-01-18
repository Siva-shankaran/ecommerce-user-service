package com.ecommerce.userservice.service;

import com.ecommerce.userservice.entity.User;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
}
