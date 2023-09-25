package com.eidiko.exception;

import java.rmi.UnexpectedException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.validation.ValidationException;

@RestControllerAdvice
public class ExceptionHandler {
	

	@org.springframework.web.bind.annotation.ExceptionHandler(ValidationException.class)
	public ResponseEntity<Map<String,Object>> GetValidationException(ValidationException ex){
		System.out.println("hello");
		Map<String, Object> map=new HashMap<>();
		map.put("result", "failed");
		map.put("error message [ "+ex.getClass().getName()+" ]" , ex.getLocalizedMessage());
		map.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,Object>> GetException(Exception ex){
		Map<String,Object> map=new HashMap<>();
		map.put("result", "failed");
		map.put("error message [ "+ex.getClass().getName()+" ]", ex.getLocalizedMessage());
		map.put("status", String.valueOf(HttpStatus.BAD_REQUEST));
		return new ResponseEntity<Map<String,Object>>(map,HttpStatus.BAD_REQUEST);
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
  @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class,})
 public Map<String,Object> validateException(MethodArgumentNotValidException ex){
		
  	Map<String,Object> map=new HashMap<>();
	map.put("result", "failed");

      ex.getBindingResult().getFieldErrors().forEach(error->{
    	  map.put(error.getField(),error.getDefaultMessage());
      });
		map.put("status", String.valueOf(HttpStatus.BAD_REQUEST));

      return map;
  }

}
