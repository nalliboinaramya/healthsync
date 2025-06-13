package com.hcl.patienttracker.service;

import java.util.List;
import java.util.Map;

import com.hcl.patienttracker.dto.ClerkDto;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Clerk;

public interface ClerkService {

	Map<String,String> registerClerk(ClerkDto clerkDTO);
    Clerk getClerkById(Long id);
    List<Clerk> getAllClerks();
    Clerk updateClerk(Long id, ClerkDto clerkDTO);
    void deleteClerk(Long id);

    void assignPatientToClerk(Long clerkId, Long patientId);
    void updatePatientDetails(Long clerkId, Long patientId, PatientDto patientDto);
     
	
	
	
}
