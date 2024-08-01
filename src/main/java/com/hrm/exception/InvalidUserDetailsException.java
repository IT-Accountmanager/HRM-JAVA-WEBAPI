package com.hrm.exception;

public class InvalidUserDetailsException extends RuntimeException {
	public InvalidUserDetailsException(String message) {
		super(message);
	}

}
