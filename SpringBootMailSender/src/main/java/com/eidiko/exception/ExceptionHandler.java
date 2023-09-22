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
	public ResponseEntity<Map<String,String>> GetValidationException(ValidationException ex){
		System.out.println("hello");
		Map<String,String> map=new HashMap<>();
		map.put("error message [ "+ex.getClass().getName()+" ]" , ex.getLocalizedMessage());
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
	}
	@org.springframework.web.bind.annotation.ExceptionHandler(Exception.class)
	public ResponseEntity<Map<String,String>> GetException(Exception ex){
		Map<String,String> map=new HashMap<>();
		map.put("error message [ "+ex.getClass().getName()+" ]", ex.getLocalizedMessage());
		return new ResponseEntity<Map<String,String>>(map,HttpStatus.BAD_REQUEST);
		
	}
	@ResponseStatus(HttpStatus.BAD_REQUEST)
  @org.springframework.web.bind.annotation.ExceptionHandler({MethodArgumentNotValidException.class,})
 public Map<String,String> validateException(MethodArgumentNotValidException ex){
  	Map<String,String> errorsMap=new HashMap<>();

      ex.getBindingResult().getFieldErrors().forEach(error->{
          errorsMap.put(error.getField(),error.getDefaultMessage());
      });
      return errorsMap;
  }

}
