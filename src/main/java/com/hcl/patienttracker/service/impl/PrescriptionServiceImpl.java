package com.hcl.patienttracker.service.impl;

import com.hcl.patienttracker.controller.PatientController;
import com.hcl.patienttracker.dto.MedicinePrescriptionRequestDto;
import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Medicine;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.entity.Prescription;
import com.hcl.patienttracker.entity.PrescriptionMedicine;
import com.hcl.patienttracker.exception.ConsultationNotFoundException;
import com.hcl.patienttracker.exception.MedicineNotFoundException;
import com.hcl.patienttracker.exception.MedicineOutOfStockException;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.repository.DoctorRepository;
import com.hcl.patienttracker.repository.MedicineRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.repository.PrescriptionRepository;
import com.hcl.patienttracker.service.PatientService;
import com.hcl.patienttracker.service.PrescriptionService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class PrescriptionServiceImpl implements PrescriptionService {

    @Autowired
    private PrescriptionRepository prescriptionRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private MedicineRepository medicineRepository;
    
  
    
    @Autowired
    private PatientService patientService;


    @Override
    public List<Prescription> getAllPrescriptions() {
        return prescriptionRepository.findAll();
    }

    @Override
    public Optional<Prescription> getPrescriptionById(Integer id) {
        return prescriptionRepository.findById(id);
    }
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);

    @Override
    public Prescription createMedicinePrescription(MedicinePrescriptionRequestDto prescribeMedicineDto) {
 	   Long patientId = prescribeMedicineDto.getPatientId();
 	   Long doctorId = prescribeMedicineDto.getDoctorId();
 	   Prescription prescription = new Prescription();
 	   Doctor d = doctorRepository.findById(doctorId).orElseThrow(()->new ResourceNotFoundException("Doctor not found with given id "+ doctorId));
 	   Patient p = patientRepository.findById(patientId).orElseThrow(()->new ResourceNotFoundException("Doctor not found with given id "+ patientId));
 	   List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();
 	   if(!patientService.hasConsulted(patientId, doctorId)){
 		   throw new ConsultationNotFoundException("Patient did not consulted the given doctor");
 	   }
 	   else
 	   {
 	   DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String formattedNow = LocalDateTime.now().format(formatter);
 	   prescription.setDate(formattedNow);
 	   prescription.setDoctor(d);
 	   prescription.setPatient(p);
 	   logger.info("Fetching all medicines Prescribed");
 	   for(Map.Entry<Long, Integer> entry:prescribeMedicineDto.getMedicineDoseMap().entrySet()) {
 		   Long id = entry.getKey();
 		   Medicine medicine  = medicineRepository.findById(id).orElseThrow(()->new MedicineNotFoundException("Requested medcines not found"));
 		   if(medicine.getStock()>= entry.getValue()) {
 		   PrescriptionMedicine prescriptionMedicine = new PrescriptionMedicine();
            prescriptionMedicine.setMedicine (medicine);
            prescriptionMedicine.setPrescription(prescription);
            prescriptionMedicine.setDosage(entry.getValue());
            prescriptionMedicine.setPrice(medicine.getPrice());
            prescriptionMedicine.setPrescribedQuantity(entry.getValue()); 
            prescriptionMedicines.add(prescriptionMedicine);
 		   }
 		   else {
 			   throw new MedicineOutOfStockException("prescribed quantity is not available at the moment");
 		   }
 	   }
 	   prescription.setPrescriptionMedicines(prescriptionMedicines);
 	   }
 	   Prescription savedPrescription = prescriptionRepository.save(prescription);
 	   return savedPrescription;
 	   
    }

    @Override
    public void deletePrescription(Integer id) {
        prescriptionRepository.deleteById(id);
    }
}