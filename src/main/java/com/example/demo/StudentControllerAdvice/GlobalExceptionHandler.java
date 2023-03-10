
package com.example.demo.StudentControllerAdvice;


import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.StudentException.ResourceNotFoundException;
import com.example.demo.StudentException.StudentErrorResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

	// handling specific exception
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> resourceNotFoundHandling(ResourceNotFoundException exception, WebRequest request){
		StudentErrorResponse studentErrorResponse = 
				new StudentErrorResponse(new Date(),exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
	}

	// handling global exception
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> globalExceptionHandling(Exception exception, WebRequest request){
		StudentErrorResponse studentErrorResponse = 
				new StudentErrorResponse(new Date(), exception.getMessage(), request.getDescription(false));
		return new ResponseEntity<>(studentErrorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?>CustomValidationErrorHandling(MethodArgumentNotValidException exception){
	StudentErrorResponse studentErrorResponse =new StudentErrorResponse(new Date(),"Validation Error",
			exception.getBindingResult().getFieldError().getDefaultMessage());
	return new ResponseEntity<> (studentErrorResponse, HttpStatus.BAD_REQUEST);
}
}









//One can get request URI and client information from WebRequest using webRequest.getDescription(true).
//
//true will show user's information such as client id and false will just print URI.
	
