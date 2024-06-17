package com.jsp.Pharmacy.Application.exception;

public class MedicalStoreIdNotFoundException extends RuntimeException{
	
	
	private String message;

	public MedicalStoreIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
