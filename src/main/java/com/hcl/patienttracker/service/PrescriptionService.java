package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.MedicinePrescriptionRequestDto;
import com.hcl.patienttracker.entity.Prescription;

import java.util.List;
import java.util.Optional;

public interface PrescriptionService {
    List<Prescription> getAllPrescriptions();
    Optional<Prescription> getPrescriptionById(Integer id);
    void deletePrescription(Integer id);
    Prescription createMedicinePrescription(MedicinePrescriptionRequestDto prescribeMedicineDto);

}