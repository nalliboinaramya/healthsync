package com.hcl.patienttracker.service.impl;


import java.util.List;

import com.hcl.patienttracker.dto.BillingDto;
import com.hcl.patienttracker.entity.*;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.repository.PatientRepository;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patienttracker.repository.BillingRepository;
import com.hcl.patienttracker.repository.MedicineRepository;
import com.hcl.patienttracker.repository.PrescriptionRepository;
import com.hcl.patienttracker.service.BillingService;

@Service
public class BillingServiceImpl implements BillingService {
   
    private BillingRepository billingRepository;

    
    private PrescriptionRepository prescriptionRepository;

   
    private MedicineRepository medicineRepository;

   
    private PatientRepository patientRepository;

   
    private ModelMapper modelMapper;
    
    
    @Autowired
    public BillingServiceImpl(BillingRepository billingRepository, PrescriptionRepository prescriptionRepository,
			MedicineRepository medicineRepository, PatientRepository patientRepository, ModelMapper modelMapper) {
		super();
		this.billingRepository = billingRepository;
		this.prescriptionRepository = prescriptionRepository;
		this.medicineRepository = medicineRepository;
		this.patientRepository = patientRepository;
		this.modelMapper = modelMapper;
	}

	private static final Logger logger = LoggerFactory.getLogger(BillingService.class);

    @Override
    public BillingDto generateBill(Integer prescriptionId) {
    	logger.info("Generating the bill for given Prescription ID: {}",prescriptionId);
    	Double totalCost = 0.0;
    	Prescription prescription = prescriptionRepository.findById(prescriptionId).orElseThrow(()->new ResourceNotFoundException("prescription with given id not found"));
    	if(prescription.getBilling() == null) {
    	Billing billing = new Billing();
    	List<PrescriptionMedicine> prescriptionMedicines = prescription.getPrescriptionMedicines();
    	for(PrescriptionMedicine prescriptionMedicine : prescriptionMedicines) {
    		totalCost = totalCost+prescriptionMedicine.getPrescribedQuantity()*prescriptionMedicine.getPrice();
    		Medicine m = prescriptionMedicine.getMedicine();
    		m.setStock(m.getStock()-prescriptionMedicine.getPrescribedQuantity());
    		medicineRepository.save(m);
    		
    	}
    	billing.setTotalCost(totalCost);
    	prescription.setBilling(billing);
    	billing.setPrescription(prescription);
    	Billing savedBilling = billingRepository.save(billing);
    	Patient patient = prescription.getPatient();
    	patient.getBills().add(billing);
    	patientRepository.save(patient);
    	
    	
    	
    	return modelMapper.map(savedBilling, BillingDto.class);
    	}
    	else {
    		return modelMapper.map(prescription.getBilling(), BillingDto.class);
    	}
    	

}
}


