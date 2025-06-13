package com.hcl.patienttracker.exception;

public class MedicineOutOfStockException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public MedicineOutOfStockException(String message) {
		super(message);
	}

}
