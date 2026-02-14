package com.ecommerce.userservice.service;

import com.ecommerce.userservice.dto.UpdateProfileRequest;
import com.ecommerce.userservice.dto.UserProfileDto;
import com.ecommerce.userservice.entity.User;

public interface UserService {
    User registerUser(User user);
    User getUserById(Long id);
    User getUserByEmail(String email);
    UserProfileDto getUserProfile(String email);

    UserProfileDto updateProfile(String email, UpdateProfileRequest request);

}
