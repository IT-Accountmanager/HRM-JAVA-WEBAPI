package com.hrm.exception;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
	private String description;
	private Boolean success;
	private HttpStatus status;
	private String cause;
	private Integer statusCode;
	private Date date;
}
