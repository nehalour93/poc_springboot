package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.CustomException;
import com.example.model.ErrorDetails;

@RestControllerAdvice(basePackageClasses=UserController.class)
public class ExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ErrorDetails> exceptionUserHandler(CustomException ex) {
		ErrorDetails error = new ErrorDetails();
		error.setErrorCode(HttpStatus.NOT_FOUND.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ErrorDetails> exceptionHandler1(Exception ex) {
		ErrorDetails error = new ErrorDetails();
		error.setErrorCode(HttpStatus.BAD_REQUEST.value());
		error.setMessage(ex.getMessage());
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
	
	
} 
