package com.ecommerce.userservice.controller;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.dto.LoginRequest;
import com.ecommerce.userservice.dto.UpdateProfileRequest;
import com.ecommerce.userservice.dto.UserProfileDto;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.security.JwtUtil;
import com.ecommerce.userservice.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private  UserService userService;
	@Autowired
    private  AuthenticationManager authenticationManager;
	@Autowired
    private  JwtUtil jwtUtil;

    @PostMapping("/register")
    public User register(@RequestBody User user) {//Reads JSON body & Converts it to User object
        return userService.registerUser(user);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return userService.getUserById(id);
    }
    
    @PostMapping("/login")
    public String login(@RequestBody LoginRequest request) {

        Authentication authentication =
                authenticationManager.authenticate(
                        new UsernamePasswordAuthenticationToken(
                                request.getEmail(),
                                request.getPassword()
                        )
                );

        return jwtUtil.generateToken(request.getEmail());
    }
    
    @GetMapping("/profile")
    public UserProfileDto getProfile(Authentication authentication) {
        String email = authentication.getName();
        return userService.getUserProfile(email);
    }

    @PutMapping("/profile")
    public UserProfileDto updateProfile(
            @RequestBody UpdateProfileRequest request,
            Authentication authentication) {

        String email = authentication.getName();
        return userService.updateProfile(email, request);
    }


}
