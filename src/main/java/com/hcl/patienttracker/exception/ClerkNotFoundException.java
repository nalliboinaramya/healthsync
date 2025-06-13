package com.hcl.patienttracker.exception;

public class ClerkNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ClerkNotFoundException(String message) {
        super(message);
    }
}
