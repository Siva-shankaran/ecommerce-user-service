package com.ecommerce.userservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@GetMapping("/health")
	public String AdminOnly() {
        return "ADMIN ACCESS GRANTED";
	}
	
}
