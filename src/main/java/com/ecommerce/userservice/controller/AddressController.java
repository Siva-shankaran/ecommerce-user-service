package com.ecommerce.userservice.controller;

import org.springframework.security.core.Authentication;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.userservice.dto.AddressRequest;
import com.ecommerce.userservice.dto.AddressResponse;
import com.ecommerce.userservice.entity.Address;
import com.ecommerce.userservice.service.AddressService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class AddressController {

	private final AddressService addressService;

	// Constructor injection
	public AddressController(AddressService addressService) {
		this.addressService = addressService;
	}

	 @PostMapping("/address")
	    public void addAddress(@RequestBody AddressRequest request, Authentication authentication) {
	        String email = authentication.getName();
	        addressService.addAddress(email, request);
	    }

	 @GetMapping("/address")
	 public List<AddressResponse> getAddresses(Authentication authentication) {
	     return addressService.getAddresses(authentication.getName());
	 }


	    @DeleteMapping("/address/{id}")
	    public String deleteAddress(@PathVariable Long id, Authentication authentication) {
	        String email = authentication.getName();
	        addressService.deleteAddress(id, email);
	        return "Address deleted successfully";
	    }
	}
