package com.example.demo.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(value = MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidException(MethodArgumentNotValidException e)
	{
		Map<String,String> map = new HashMap<>();
		e.getBindingResult().getFieldErrors().forEach(error -> map.put(error.getField(), error.getDefaultMessage()));
		
		return new ResponseEntity<>(map, HttpStatus.BAD_REQUEST);
	}

	// Handle Manual exception
	
	@ExceptionHandler(value = UserNotFoundException.class)
	public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e )
	{
	//   e.getMessage();
	
	return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
		
	}
	
	//handle global exception
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception e)
	{
		// e.getMessage();
			
			return new ResponseEntity<>("Something Went Wrong"+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
}
