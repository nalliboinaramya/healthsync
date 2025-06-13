package com.hcl.patienttracker.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;

@Entity
public class PrescriptionMedicine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "prescription_id")
    @JsonBackReference(value = "prescription-medicines")
    private Prescription prescription;

    @ManyToOne
    @JoinColumn(name = "medicine_id")
    @JsonBackReference(value = "medicine-prescription")
    private Medicine medicine;

    @NotNull
    private Integer prescribedQuantity;
    
    @NotNull
    private Integer dosage;
    
    @NotNull(message = "Price is mandatory")
    private Double price;

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

	public Medicine getMedicine() {
		return medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public Integer getPrescribedQuantity() {
		return prescribedQuantity;
	}

	public void setPrescribedQuantity(Integer prescribedQuantity) {
		this.prescribedQuantity = prescribedQuantity;
	}

	public Integer getDosage() {
		return dosage;
	}

	public void setDosage(Integer dosage) {
		this.dosage = dosage;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	
    
    

}
