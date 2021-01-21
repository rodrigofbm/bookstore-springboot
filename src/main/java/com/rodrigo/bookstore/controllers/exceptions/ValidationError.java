package com.rodrigo.bookstore.controllers.exceptions;

import java.util.List;

public class ValidationError extends StandardError{

	private List<String> errors;
	
	public ValidationError() {
	}

	public ValidationError(Integer status, String message, Long timestamp) {
		super(status, message, timestamp);

	}

	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}
}
