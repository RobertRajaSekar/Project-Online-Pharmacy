package com.jsp.Pharmacy.Application.exception;

public class MedicineIdNotFoundException extends RuntimeException{
	
	private String message;

	public MedicineIdNotFoundException(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
