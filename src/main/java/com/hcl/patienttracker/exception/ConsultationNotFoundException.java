package com.hcl.patienttracker.exception;

public class ConsultationNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ConsultationNotFoundException(String message) {
		super(message);
	}

}
