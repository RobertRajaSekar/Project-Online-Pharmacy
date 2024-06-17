package com.jsp.Pharmacy.Application.exception;

public class CustomerEmailNotFoundException extends RuntimeException{

	private String message;

	public CustomerEmailNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	
}
