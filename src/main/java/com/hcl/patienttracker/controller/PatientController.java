package com.hcl.patienttracker.controller;

import java.util.List;


import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;
import com.hcl.patienttracker.service.PatientService;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("/api")
public class PatientController {
	
	
	private PatientService patientService;
	private static final Logger logger = LoggerFactory.getLogger(PatientController.class);
	
	
	@Autowired
	public PatientController(PatientService patientService) {
		this.patientService = patientService;
	}



	@PostMapping("/patients")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> addNewPatient(@Valid @RequestBody PatientDto patientDto) throws PatientAlreadyexistsException {
		    PatientDto patientDto2 = patientService.addNewPatient(patientDto);
			return new ResponseEntity<PatientDto>(patientDto2, HttpStatus.CREATED);
		
	}
	
	@GetMapping("/patients/{id}")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> getPatientDetails(@PathVariable Long id){
		logger.info("Fetching patient with ID: {}",id);
		PatientDto patientDto =  patientService.getPatient(id);
		return new ResponseEntity<PatientDto>(patientDto, HttpStatus.OK);
		
	}
	
	@GetMapping("/patients")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<List<PatientDto>> getAllPatients(){
		List<PatientDto> patientDtos = patientService.getAllPatients();
		logger.info("List of Patients = {}" ,patientDtos.size());
		return new ResponseEntity<List<PatientDto>>(patientDtos,HttpStatus.OK);
	}
	
	@PutMapping("/patients/{id}")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updatePatientDetails(@PathVariable Long id,@Valid @RequestBody PatientDto patientDto){
		PatientDto patientDtoNew =  patientService.updatePatientDto(id, patientDto);
		return new ResponseEntity<PatientDto>(patientDtoNew, HttpStatus.OK);
	}
	
	@DeleteMapping("/patients/{id}")
	 @PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deletePatient(@PathVariable Long id){
		patientService.deletePatient(id);
		return new ResponseEntity<String>("Delete sucessfully", HttpStatus.OK);
	}
	
	
	

}
