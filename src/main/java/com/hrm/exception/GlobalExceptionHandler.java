package com.hrm.exception;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<Map<String, Object>> notFoundHandler(ResourceNotFoundException x) {
		Map<String, Object> map = new HashMap<>();
		map.put("Message", x.getMessage());
		map.put("Success", false);
		map.put("Status", HttpStatus.NOT_FOUND);
		map.put("Cause", x.getCause());
		map.put("Status Code", HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(map);
	}

	@ExceptionHandler(UserAlreadyExistedException.class)
	public ResponseEntity<ExceptionResponse> userAlreadyExistedHandler(UserAlreadyExistedException u) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(u.getMessage(), false, HttpStatus.BAD_REQUEST,
				"Duplipate Username", 400, new Date());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(InvalidUserDetailsException.class)
	public ResponseEntity<ExceptionResponse> invalidUserInput(InvalidUserDetailsException i) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(i.getMessage(), false, HttpStatus.BAD_REQUEST,
				"Invalid Inputs", 400, new Date());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BadCredentialsException.class)
	public ResponseEntity<ExceptionResponse> badCredentials(BadCredentialsException b) {
		ExceptionResponse exceptionResponse = new ExceptionResponse(b.getMessage(), false, HttpStatus.BAD_REQUEST,
				"ENTERED USERNAME AND PASSWORD DOES NOT MATCHED!", 400, new Date());
		return new ResponseEntity<ExceptionResponse>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}
