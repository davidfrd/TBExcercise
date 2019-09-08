package com.davidredondo.exception;

public class ValidationException extends RuntimeException {

	private static final long serialVersionUID = -2080889075364661825L;
	
	private String message;
	
	private Object invalidObject;
	
	private ValidationException(String message) {
		this.message = message;
	}
	
	private ValidationException(Object invalidObject, String message) {
		this.message = message;
		this.invalidObject = invalidObject;
	}
	
	public String getMessage() {
		return this.message;
	}
	
	public Object getInvalidObject() {
		return this.invalidObject;
	}
	
	public static void throwWithMessage(String message) {
		ValidationException ex = new ValidationException(message);
		throw ex;
	}
	
	public static void throwWithInvalidObjectAndMessage(Object invalidObject, String message) {
		ValidationException ex = new ValidationException(invalidObject, message);
		throw ex;
	}

}
