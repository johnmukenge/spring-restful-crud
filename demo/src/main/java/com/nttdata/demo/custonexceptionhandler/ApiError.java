package com.nttdata.demo.custonexceptionhandler;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ApiError {

	private HttpStatus httpStatus;
	private Object exception;
	private String message;
	private Object errors;

	public ApiError(HttpStatus httpStatus, Object exception, String message, Object errors) {
		this.httpStatus = httpStatus;
		this.exception = exception.getClass().getCanonicalName();
		this.message = message;
		this.errors = errors;
	}

}
