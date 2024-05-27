package com.hrm.exception;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
}
