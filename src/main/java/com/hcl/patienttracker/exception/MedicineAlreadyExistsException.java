package com.hcl.patienttracker.exception;

public class MedicineAlreadyExistsException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MedicineAlreadyExistsException(String message) {
        super(message);
    }
}