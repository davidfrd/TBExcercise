package com.davidredondo.entity.response;

public class CustomValidationErrorResponse {

	private String errorMessage;

	private Object invalidObject;

	private String className;

	private CustomValidationErrorResponse() {
	};

	public static CustomValidationErrorResponse build(Object invalidObject, String errorMessage) {
		return new CustomValidationErrorResponse().setErrorMessage(errorMessage).setInvalidObject(invalidObject)
				.setClassName(invalidObject.getClass().getSimpleName());
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public CustomValidationErrorResponse setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
		return this;
	}

	public Object getInvalidObject() {
		return invalidObject;
	}

	public CustomValidationErrorResponse setInvalidObject(Object invalidObject) {
		this.invalidObject = invalidObject;
		return this;
	}

	public String getClassName() {
		return className;
	}

	private CustomValidationErrorResponse setClassName(String className) {
		this.className = className;
		return this;
	}
}
