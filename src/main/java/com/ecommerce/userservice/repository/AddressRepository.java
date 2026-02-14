package com.ecommerce.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.userservice.entity.Address;
import com.ecommerce.userservice.entity.User;

public interface AddressRepository extends JpaRepository<Address, Long> {

    List<Address> findByUser(User user);
}
