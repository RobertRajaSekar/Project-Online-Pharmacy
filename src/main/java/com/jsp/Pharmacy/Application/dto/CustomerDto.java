package com.jsp.Pharmacy.Application.dto;

import java.util.List;

import org.springframework.stereotype.Component;

import jakarta.persistence.OneToMany;

@Component
public class CustomerDto {
	private int customerId;
	private String customerName;
	private String customerEMail;
	
	@OneToMany
	private List<AddressDto> addressDtos;
	
	@OneToMany
	private List<BookingDto> bookingDtos;

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEMail() {
		return customerEMail;
	}

	public void setCustomerEMail(String customerEMail) {
		this.customerEMail = customerEMail;
	}

	public List<AddressDto> getAddressDtos() {
		return addressDtos;
	}

	public void setAddressDtos(List<AddressDto> addressDtos) {
		this.addressDtos = addressDtos;
	}

	public List<BookingDto> getBookingDtos() {
		return bookingDtos;
	}

	public void setBookingDtos(List<BookingDto> bookingDtos) {
		this.bookingDtos = bookingDtos;
	}

}
