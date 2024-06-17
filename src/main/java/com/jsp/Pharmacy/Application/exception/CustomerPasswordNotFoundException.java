package com.jsp.Pharmacy.Application.exception;

public class CustomerPasswordNotFoundException extends RuntimeException{
	
	private String message;

	public CustomerPasswordNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
