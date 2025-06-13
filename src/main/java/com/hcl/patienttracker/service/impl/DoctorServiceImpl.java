package com.hcl.patienttracker.service.impl;


import com.hcl.patienttracker.exception.DoctorAlreadyExistsException;
import com.hcl.patienttracker.exception.DoctorNotFoundException;
import com.hcl.patienttracker.exception.PatientNotFoundException;

import com.hcl.patienttracker.dto.DoctorDto;
import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.repository.DoctorRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.DoctorService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DoctorServiceImpl implements DoctorService {

	 @Autowired
	    private DoctorRepository doctorRepository;
	    
	    @Autowired
	    private PatientRepository patientRepository;
	    
	    @Autowired
	    private ModelMapper modelMapper;

	    @Override
	    public DoctorDto createDoctor(DoctorDto doctorDTO) throws DoctorAlreadyExistsException {
	        Optional<Doctor> existingDoctor = doctorRepository.findByFirstNameAndLastNameAndContactNumber(
	                doctorDTO.getFirstName(), doctorDTO.getLastName(), doctorDTO.getContactNumber());
	        if (existingDoctor.isPresent()) {
	            throw new DoctorAlreadyExistsException("Doctor with given first name, last name, and contact number already exists.");
	        }
	        Doctor doctor = modelMapper.map(doctorDTO, Doctor.class);
	        Doctor savedDoctor = doctorRepository.save(doctor);
	        return modelMapper.map(savedDoctor, DoctorDto.class);
	    }

	    @Override
	    public DoctorDto getDoctorById(Long doctorId) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));
	        return convertToDTO(doctor);
	    }

	    @Override
	    public List<DoctorDto> getAllDoctors() {
	        List<Doctor> doctors = doctorRepository.findAll();
	        return doctors.stream()
	                .map(this::convertToDTO)
	                .collect(Collectors.toList());
	    }

	    @Override
	    public DoctorDto updateDoctor(Long doctorId, DoctorDto doctorDTO) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));
	        doctor.setFirstName(doctorDTO.getFirstName());
	        doctor.setLastName(doctorDTO.getLastName());
	        doctor.setAge(doctorDTO.getAge());
	        doctor.setGender(doctorDTO.getGender());
	        doctor.setContactNumber(doctorDTO.getContactNumber());
	        doctor.setSpecialization(doctorDTO.getSpecialization());
	        Doctor updatedDoctor = doctorRepository.save(doctor);
	        return convertToDTO(updatedDoctor);
	    }

	    @Override
	    public void deleteDoctor(Long doctorId) {
	        Doctor doctor = doctorRepository.findById(doctorId)
	                .orElseThrow(() -> new DoctorNotFoundException("Doctor not found with id: " + doctorId));
	        doctorRepository.delete(doctor);
	    }

	    private DoctorDto convertToDTO(Doctor doctor) {
	        DoctorDto doctorDTO = new DoctorDto();
	        doctorDTO.setDoctorId(doctor.getDoctorId());
	        doctorDTO.setFirstName(doctor.getFirstName());
	        doctorDTO.setLastName(doctor.getLastName());
	        doctorDTO.setAge(doctor.getAge());
	        doctorDTO.setGender(doctor.getGender());
	        doctorDTO.setContactNumber(doctor.getContactNumber());
	        doctorDTO.setSpecialization(doctor.getSpecialization());
	        return doctorDTO;
	    }

	    private Doctor convertToEntity(DoctorDto doctorDTO) {
	        Doctor doctor = new Doctor();
	        doctor.setFirstName(doctorDTO.getFirstName());
	        doctor.setLastName(doctorDTO.getLastName());
	        doctor.setAge(doctorDTO.getAge());
	        doctor.setGender(doctorDTO.getGender());
	        doctor.setContactNumber(doctorDTO.getContactNumber());
	        doctor.setSpecialization(doctorDTO.getSpecialization());
	        return doctor;
	    }
	    
    
    @Override
    public DoctorDto assignpatient(Long doctorId, Long patientId) {
    	
    	Patient patient = patientRepository.findById(patientId)
    			.orElseThrow(()->new PatientNotFoundException("patient not found with id "+patientId));
    	Doctor doctor =  doctorRepository.findById(doctorId).
    			orElseThrow(()->new DoctorNotFoundException("Doctor not found with id: " + doctorId));
    	doctor.getPatients().add(patient);
    	Doctor newDoctor = doctorRepository.save(doctor);
    	
    	return modelMapper.map(newDoctor, DoctorDto.class);
    	
    	
    }
}