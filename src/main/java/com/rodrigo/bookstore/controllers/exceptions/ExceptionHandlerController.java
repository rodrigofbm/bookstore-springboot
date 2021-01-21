package com.rodrigo.bookstore.controllers.exceptions;

import java.util.Arrays;
import java.util.stream.Collectors;

import javax.servlet.ServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.rodrigo.bookstore.services.exceptions.DataIntegrityViolationException;
import com.rodrigo.bookstore.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ExceptionHandlerController {
	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<StandardError> objectNotFoundException(ObjectNotFoundException e, ServletRequest request) {
		StandardError error = new StandardError(HttpStatus.NOT_FOUND.value(), Arrays.asList(e.getMessage()),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
	}

	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity<StandardError> dataIntegrityViolationException(DataIntegrityViolationException e,
			ServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), Arrays.asList(e.getMessage()),
				System.currentTimeMillis());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<StandardError> methodArgumentNotValidException(MethodArgumentNotValidException e, ServletRequest request) {
		StandardError error = new StandardError(HttpStatus.BAD_REQUEST.value(), null,
				System.currentTimeMillis());
		
		error.setMessages(e.getAllErrors().stream().map(x -> x.getDefaultMessage()).collect(Collectors.toList()));

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
	}
}
