package com.ecommerce.userservice.service;

import java.util.List;

import com.ecommerce.userservice.dto.AddressRequest;
import com.ecommerce.userservice.dto.AddressResponse;
import com.ecommerce.userservice.entity.Address;

public interface AddressService {

	 void addAddress(String email, AddressRequest request);

	    List<AddressResponse> getAddresses(String email);

	    void deleteAddress(Long id, String email);
}
