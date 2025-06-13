package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.DoctorDto;
import com.hcl.patienttracker.exception.DoctorAlreadyExistsException;
import com.hcl.patienttracker.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doctors")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> createDoctor(@Validated @RequestBody DoctorDto doctorDTO) throws DoctorAlreadyExistsException {  
    	DoctorDto createdDoctor = doctorService.createDoctor(doctorDTO);  
    return new ResponseEntity<>(createdDoctor, HttpStatus.CREATED);}

    @GetMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> getDoctorById(@PathVariable Long doctorId) {
        DoctorDto doctorDTO = doctorService.getDoctorById(doctorId);
        return new ResponseEntity<>(doctorDTO, HttpStatus.OK);
    }
    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<DoctorDto>> getAllDoctors() {
        List<DoctorDto> doctors = doctorService.getAllDoctors();
        return new ResponseEntity<>(doctors, HttpStatus.OK);
    }

    @PutMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<DoctorDto> updateDoctor(@PathVariable Long doctorId, @Validated @RequestBody DoctorDto doctorDTO) {
        DoctorDto updatedDoctor = doctorService.updateDoctor(doctorId, doctorDTO);
        return new ResponseEntity<>(updatedDoctor, HttpStatus.OK);
    }

    @DeleteMapping("/{doctorId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteDoctor(@PathVariable Long doctorId) {
        doctorService.deleteDoctor(doctorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    
    @PutMapping("/{doctorId}/assignPatient/{patientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> assignPatientToDoctor(@PathVariable Long doctorId, @PathVariable Long patientId) {
    		DoctorDto doctorDto = doctorService.assignpatient(doctorId, patientId);
    		return new ResponseEntity<>(doctorDto,HttpStatus.ACCEPTED);
    	
    	
    }
}
