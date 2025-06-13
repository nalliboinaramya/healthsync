package com.hcl.patienttracker.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.patienttracker.dto.ClerkDto;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Clerk;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.ClerkAlreadyExistsException;
import com.hcl.patienttracker.exception.ClerkNotFoundException;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.exception.UnauthorizedAccessException;
import com.hcl.patienttracker.repository.ClerkRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.ClerkService;
@Service
public class ClerkServiceImpl implements ClerkService{
	
	@Autowired
    private ClerkRepository clerkRepository;
	
	@Autowired
	private PatientRepository patientRepository;

    @Override
    public List<Clerk> getAllClerks() {
        return clerkRepository.findAll();
    }

    @Override
    public Map<String, String> registerClerk(ClerkDto clerkDTO) 
    {  
      if (clerkRepository.existsByContactNumber(clerkDTO.getContactNumber()))
     {       
     throw new ClerkAlreadyExistsException("Clerk already exists with contact number: " + clerkDTO.getContactNumber());  
      }   
     Clerk clerk = new Clerk(); 
       clerk.setFirstName(clerkDTO.getFirstName());  
      clerk.setLastName(clerkDTO.getLastName());  
      clerk.setAge(clerkDTO.getAge()); 
       clerk.setContactNumber(clerkDTO.getContactNumber()); 
       clerk.setGender(clerkDTO.getGender());   
     clerkRepository.save(clerk);  
      Map<String, String> response = new HashMap<>();  
      response.put("message", "Your details submitted successfully");   
     return response;
    }

    @Override
    public Clerk getClerkById(Long id) {
        return clerkRepository.findById(id)
                .orElseThrow(() -> new ClerkNotFoundException("Clerk not found with id: " + id));
    }

    @Override
    public Clerk updateClerk(Long id, ClerkDto clerkDTO) {
        Clerk existingClerk = clerkRepository.findById(id)
                .orElseThrow(() -> new ClerkNotFoundException("Clerk not found with id: " + id));
        existingClerk.setFirstName(clerkDTO.getFirstName());
        existingClerk.setLastName(clerkDTO.getLastName());
        existingClerk.setAge(clerkDTO.getAge());
        existingClerk.setContactNumber(clerkDTO.getContactNumber());
        existingClerk.setGender(clerkDTO.getGender());
        return clerkRepository.save(existingClerk);
    }

    @Override
    public void deleteClerk(Long id) {
        Clerk clerk = clerkRepository.findById(id)
                .orElseThrow(() -> new ClerkNotFoundException("Clerk not found with id: " + id));
        clerkRepository.delete(clerk);
    }


    @Override
    public void assignPatientToClerk(Long clerkId,Long patientId)
    {    
    Clerk clerk=clerkRepository.findById(clerkId)          
      .orElseThrow(()->new ClerkNotFoundException("clerk not found with "+clerkId));  
      Patient patient=patientRepository.findById(patientId)            
    .orElseThrow(()->new PatientNotFoundException("patient not found with "+patientId)); 
       patient.setClerk(clerk);   
     patientRepository.save(patient);
    }

    @Override
    public void updatePatientDetails(Long clerkId, Long patientId, PatientDto patientDto) 
    {   
     Clerk clerk = clerkRepository.findById(clerkId)          
      .orElseThrow(() -> new ClerkNotFoundException("Clerk not found with id " + clerkId));
        Patient patient = patientRepository.findById(patientId)          
      .orElseThrow(() -> new PatientNotFoundException("Patient not found with id " + patientId));  
      if (!patient.getClerk().getId().equals(clerkId)) 
    {       
     throw new UnauthorizedAccessException("Clerk is not authorized to update this patient's details"); 
       }    
    patient.setName(patientDto.getName());   
     patient.setDob(patientDto.getDob());   
     patient.setContactNumber(patientDto.getContactNumber());   
     patient.setEmail(patientDto.getEmail());   
     patient.setGender(patientDto.getGender());   
     patient.setCity(patientDto.getCity());   
     patientRepository.save(patient);
    }
	
	
}
