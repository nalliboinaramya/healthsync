package com.hcl.patienttracker.dto;

import org.springframework.http.HttpStatus;

public class ExceptionResponseDto {
	
	private String name;
	private String message;
	private HttpStatus status;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMessage() {
		return message;
	}
	public HttpStatus getStatus() {
		return status;
	}
	public void setStatus(HttpStatus status) {
		this.status = status;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public ExceptionResponseDto(String name, String message, HttpStatus status) {
		super();
		this.name = name;
		this.message = message;
		this.status = status;
	}

}
