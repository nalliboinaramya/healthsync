package com.hcl.patienttracker.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
//@JsonIgnoreProperties({"prescription"})
public class Billing {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;

	    @OneToOne(mappedBy ="billing")
	    @JsonManagedReference(value = "billing-presciption")
	    private Prescription prescription;

	    private double totalCost;

	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public Prescription getPrescription() {
	        return prescription;
	    }

	    public void setPrescription(Prescription prescription) {
	        this.prescription = prescription;
	    }

	    public double getTotalCost() {
	        return totalCost;
	    }

	    public void setTotalCost(double totalCost) {
	        this.totalCost = totalCost;
	    }

	    
	    
}



	


