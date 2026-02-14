package com.ecommerce.userservice.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.UpdateProfileRequest;
import com.ecommerce.userservice.dto.UserProfileDto;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserRepository userRepository;
	@Autowired
	private   PasswordEncoder passwordEncoder;



	@Override
	public User registerUser(User user) {

	    if (user.getPassword() == null || user.getPassword().isBlank()) {
	        throw new RuntimeException("Password is mandatory");
	    }

	    user.setPassword(passwordEncoder.encode(user.getPassword()));
	    return userRepository.save(user);
	}

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
    @Override
    public User getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
	@Override
	public UserProfileDto getUserProfile(String email) {
		User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    return new UserProfileDto(
	            user.getId(),
	            user.getName(),
	            user.getEmail(),
	            user.getRole(),
	            user.getActive()
	    );	}
	@Override
	public UserProfileDto updateProfile(String email, UpdateProfileRequest request) {
		
		User user = userRepository.findByEmail(email)
	            .orElseThrow(() -> new RuntimeException("User not found"));

	    user.setName(request.getName());
	    user.setPhone(request.getPhone());

	    userRepository.save(user);

	    return new UserProfileDto(
	            user.getId(),
	            user.getName(),
	            user.getEmail(),
	            user.getRole(),
	            user.getActive()
	    );
	}

}
