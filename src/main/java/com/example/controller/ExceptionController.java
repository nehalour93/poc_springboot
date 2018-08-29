package com.example.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.example.exception.CustomException;
import com.example.model.ErrorDetails;

@RestControllerAdvice(basePackageClasses=UserController.class)
public class ExceptionController extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler(CustomException.class)
	public final ResponseEntity<ErrorDetails> handleUserNotFoundException(CustomException ex, WebRequest request) {
	    ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	        request.getDescription(true));
	    return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
	  }
	
	
	
} 
