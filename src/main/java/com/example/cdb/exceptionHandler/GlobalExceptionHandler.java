package com.example.cdb.exceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.example.cdb.exception.InvalidCredentialException;
import com.example.cdb.exception.NotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleAllException(Exception ex){
		
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFoundException(NotFoundException ex) {
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
		
	}
	
	@ExceptionHandler(InvalidCredentialException.class)
	public ResponseEntity<ErrorResponse> handleInvalidCredentialException(InvalidCredentialException ex){
		ErrorResponse error = new ErrorResponse();
		error.setMessage(ex.getMessage());
		error.setTimeStamp(System.currentTimeMillis());
		error.setStatus(HttpStatus.BAD_REQUEST.value());
		return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
	}
	

}








