package com.hcl.patienttracker.exception;

public class UnauthorizedAccessException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public UnauthorizedAccessException(String message) {
        super(message);
    }
}
