package com.hcl.patienttracker.dto;


import com.hcl.patienttracker.entity.Prescription;



public class BillingDto {
    private Long id;
    
    private PrescriptionDto prescription;

    private double totalCost;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    

	public PrescriptionDto getPrescription() {
		return prescription;
	}

	public void setPrescription(PrescriptionDto prescription) {
		this.prescription = prescription;
	}

	public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }
}
