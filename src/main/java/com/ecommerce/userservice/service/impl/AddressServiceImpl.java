package com.ecommerce.userservice.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.ecommerce.userservice.dto.AddressRequest;
import com.ecommerce.userservice.dto.AddressResponse;
import com.ecommerce.userservice.entity.Address;
import com.ecommerce.userservice.entity.User;
import com.ecommerce.userservice.repository.AddressRepository;
import com.ecommerce.userservice.repository.UserRepository;
import com.ecommerce.userservice.service.AddressService;

import lombok.RequiredArgsConstructor;

@Service
public class AddressServiceImpl implements AddressService {

    private final UserRepository userRepository;
    private final AddressRepository addressRepository;

    public AddressServiceImpl(UserRepository userRepository,
            AddressRepository addressRepository) {
   this.userRepository = userRepository;
   this.addressRepository = addressRepository;
    }
    
    @Override
    public void addAddress(String email, AddressRequest request) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (Boolean.TRUE.equals(request.getIsDefault())) {
            List<Address> addresses = addressRepository.findByUser(user);
            addresses.forEach(a -> a.setIsDefault(false));
            addressRepository.saveAll(addresses);
        }

        Address address = new Address();
        address.setAddressLine(request.getAddressLine());
        address.setCity(request.getCity());
        address.setState(request.getState());
        address.setPincode(request.getPincode());
        address.setCountry(request.getCountry());
        address.setIsDefault(request.getIsDefault());
        address.setUser(user);

        addressRepository.save(address);
    }

    @Override
    public List<AddressResponse> getAddresses(String email) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return addressRepository.findByUser(user)
                .stream()
                .map(a -> new AddressResponse(
                        a.getId(),
                        a.getAddressLine(),
                        a.getCity(),
                        a.getState(),
                        a.getPincode(),
                        a.getCountry(),
                        a.getIsDefault()
                ))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteAddress(Long id, String email) {

        Address address = addressRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Address not found"));

        if (!address.getUser().getEmail().equals(email)) {
            throw new RuntimeException("Unauthorized");
        }

        addressRepository.delete(address);
    }
}