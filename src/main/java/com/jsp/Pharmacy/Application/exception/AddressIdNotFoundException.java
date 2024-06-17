package com.jsp.Pharmacy.Application.exception;

public class AddressIdNotFoundException extends RuntimeException{

	private String message;
	
	public AddressIdNotFoundException(String message) {
		super();
		this.message=message;
	}

	public String getMessage() {
		return message;
	}
}
