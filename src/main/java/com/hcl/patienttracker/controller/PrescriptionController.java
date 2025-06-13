package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.MedicinePrescriptionRequestDto;

import com.hcl.patienttracker.entity.Prescription;
import com.hcl.patienttracker.service.PrescriptionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prescription")
public class PrescriptionController {

    @Autowired
    private PrescriptionService prescriptionService;

    @GetMapping("/prescriptions")
    @PreAuthorize("hasRole('ADMIN')")
    public List<Prescription> getAllPrescriptions() {
        return prescriptionService.getAllPrescriptions();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prescription> getPrescriptionById(@PathVariable Integer id) {
        Optional<Prescription> prescription = prescriptionService.getPrescriptionById(id);
        return prescription.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deletePrescription(@PathVariable Integer id) {
        try {
            prescriptionService.deletePrescription(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/addMedicinePrescrition")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Prescription> addMedicinePrescription(@RequestBody MedicinePrescriptionRequestDto medicinePrescriptionDto) {
        Prescription prescription = prescriptionService.createMedicinePrescription(medicinePrescriptionDto);
        return new ResponseEntity<Prescription>(prescription, HttpStatus.CREATED);
    }

}