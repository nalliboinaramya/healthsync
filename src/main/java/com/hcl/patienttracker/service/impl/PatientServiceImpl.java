package com.hcl.patienttracker.service.impl;


import java.util.*;
import java.util.stream.Collectors;

import com.hcl.patienttracker.controller.PatientController;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.ConsultationNotFoundException;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.PatientService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import jakarta.transaction.Transactional;

@Service
@Transactional
public class PatientServiceImpl implements PatientService {

    private PatientRepository patientRepository;
    private ModelMapper modelMapper;
    private static final Logger logger = LoggerFactory.getLogger(PatientController.class);


    @Autowired
    public PatientServiceImpl(PatientRepository patientRepository, ModelMapper modelMapper) {
        super();
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PatientDto addNewPatient(PatientDto patientDto) throws PatientAlreadyexistsException {
        Patient savedPatient = null;
        if (patientRepository.findByNameAndDobAndContactNumber(
                patientDto.getName(), patientDto.getDob(), patientDto.getContactNumber()) == null) {
            Patient patient = modelMapper.map(patientDto, Patient.class);
            savedPatient = patientRepository.save(patient);
        } else {
            throw new PatientAlreadyexistsException("patient with given name or contact number details already exists");
        }
        return modelMapper.map(savedPatient, PatientDto.class);
    }

    @Override
    public PatientDto getPatient(Long id) {
    	Patient patient;
        Optional<Patient> optionalPatient = patientRepository.findById(id);
        if(optionalPatient.isEmpty()) {
        	logger.warn("patient with ID {} not found ",id);
        	throw new PatientNotFoundException("Patient is not found with given id " + id);
        }
        else
        {
        	patient = optionalPatient.get();
        }
        PatientDto patientDto = modelMapper.map(patient, PatientDto.class);
        return patientDto;
    }

    @Override
    public List<PatientDto> getAllPatients() {
        List<Patient> patients = patientRepository.findAll();
        System.out.println(patients);
        List<PatientDto> patientDtos = patients.stream().map(patient -> modelMapper.map(patient, PatientDto.class)).collect(Collectors.toList());
        return patientDtos;
    }

    @Override
    public PatientDto updatePatientDto(Long id, PatientDto patientDto) {

        Patient existingPatient = patientRepository.findById(id).orElseThrow(() -> new PatientNotFoundException("Patient is not found with given id " + id));
        existingPatient.setName(patientDto.getName());
        existingPatient.setDob(patientDto.getDob());
        existingPatient.setCity(patientDto.getCity());
        existingPatient.setContactNumber(patientDto.getContactNumber());
        existingPatient.setEmail(patientDto.getEmail());
        existingPatient.setGender(patientDto.getGender());
        existingPatient.setId(id);

        Patient newPatient = patientRepository.save(existingPatient);
        return modelMapper.map(newPatient, PatientDto.class);

    }

    @Override
    public void deletePatient(Long id) {
        Optional<Patient> optional = patientRepository.findById(id);
        if (optional.isPresent()) {
            patientRepository.delete(optional.get());
        } else {
            throw new PatientNotFoundException("Patient is not found with given id " + id);
        }
    }
    
    
    @Override
	public Boolean hasConsulted(Long patientId, Long doctorId) {
		 logger.debug("Searching for patient and docotor consultation");
		 Patient patient = patientRepository.findById(patientId).orElseThrow(()->new PatientNotFoundException("Patient not found with given id "+ patientId));
		 List<Doctor> doctors = patient.getDoctors();
		 if(doctors.isEmpty()) {
			throw new ConsultationNotFoundException("Patient had No consultations");
		 }
		 Boolean isConsulted = doctors.stream().map(doctor -> doctor.getDoctorId()).anyMatch(doctorsId->doctorsId.equals(doctorId));
		 return isConsulted;
		   
		   
	}


}
