package com.ecommerce.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
	
    private Long id;
    private String name;
    private String email;
    private String role;
    private boolean active;
    
    public UserProfileDto(Long id, String name, String email, String role, Boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.role = role;
        this.active = active;
    }

    // getters & setters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getRole() { return role; }
    public Boolean getActive() { return active; }
}