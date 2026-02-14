package com.ecommerce.userservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressResponse {

    private Long id;
    private String addressLine;
    private String city;
    private String state;
    private String pincode;
    private String country;
    private Boolean isDefault;
    
    public AddressResponse(Long id,
            String addressLine,
            String city,
            String state,
            String pincode,
            String country,
            Boolean isDefault) {

this.id = id;
this.addressLine = addressLine;
this.city = city;
this.state = state;
this.pincode = pincode;
this.country = country;
this.isDefault = isDefault;
}
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAddressLine() {
		return addressLine;
	}
	public void setAddressLine(String addressLine) {
		this.addressLine = addressLine;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public Boolean getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
    
    
}
