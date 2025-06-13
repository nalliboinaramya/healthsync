package com.hcl.patienttracker.dto;

import java.util.ArrayList;
import java.util.List;


import com.hcl.patienttracker.entity.PrescriptionMedicine;


public class PrescriptionDto {
    private Integer prescriptionId;
    private String date;
    
    private List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();

    public Integer getPrescriptionId() {
        return prescriptionId;
    }

    public void setPrescriptionId(Integer prescriptionId) {
        this.prescriptionId = prescriptionId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

	public List<PrescriptionMedicine> getPrescriptionMedicines() {
		return prescriptionMedicines;
	}

	public void setPrescriptionMedicines(List<PrescriptionMedicine> prescriptionMedicines) {
		this.prescriptionMedicines = prescriptionMedicines;
	}

    

	
    
}
