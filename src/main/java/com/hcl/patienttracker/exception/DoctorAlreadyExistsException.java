package com.hcl.patienttracker.exception;

public class DoctorAlreadyExistsException extends  RuntimeException{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DoctorAlreadyExistsException(String message)
    {
        super(message);
    }
}
