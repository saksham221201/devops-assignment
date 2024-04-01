package com.nagarro.customermanagementservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.nagarro.customermanagementservice.payload.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<?> handleException(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
				HttpStatus.INTERNAL_SERVER_ERROR.value(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	}
	
	@ExceptionHandler(RuntimeException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleGenericException(Exception e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
				HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResponse errorResponse = new ErrorResponse(e.getBindingResult().getFieldError().getDefaultMessage(),
				HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(EmptyInputException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleEmptyInputException(EmptyInputException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorMessage(),
				e.getErrorCode(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getErrorMessage(),
				e.getErrorCode(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<?> handleIllegalArgumentException(IllegalArgumentException e){
		ErrorResponse errorResponse = new ErrorResponse(e.getMessage(),
				HttpStatus.BAD_REQUEST.value(), LocalDateTime.now());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
	}
}
