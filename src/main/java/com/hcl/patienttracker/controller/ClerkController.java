package com.hcl.patienttracker.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patienttracker.dto.ClerkDto;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Clerk;
import com.hcl.patienttracker.service.ClerkService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clerks")
public class ClerkController {
	
	@Autowired
    private ClerkService clerkService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Clerk>> getAllClerks() {
        List<Clerk> clerks = clerkService.getAllClerks();
        return ResponseEntity.ok(clerks);
    }

    @PostMapping("/save")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Map<String, String>> saveClerk(@Valid @RequestBody ClerkDto clerkDTO) {
        Map<String, String> response = clerkService.registerClerk(clerkDTO);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Clerk> getClerkById(@PathVariable Long id) {
        Clerk clerk = clerkService.getClerkById(id);
        return ResponseEntity.ok(clerk);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Clerk> updateClerk(@PathVariable Long id, @Valid @RequestBody ClerkDto clerkDTO) {
        Clerk updatedClerk = clerkService.updateClerk(id, clerkDTO);
        return ResponseEntity.ok(updatedClerk);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteClerk(@PathVariable Long id) {
        clerkService.deleteClerk(id);
        return ResponseEntity.ok("Clerk deleted successfully");
    }
    
    @PostMapping("/admin/assignPatient/{clerkId}/{patientId}")   //admin assign patients to clerk,when new patient is registered ,admin select clerk and assign patient
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> assignPatientToClerk(@PathVariable Long clerkId,@PathVariable Long patientId)
    {  
      clerkService.assignPatientToClerk(clerkId,patientId); 
       return ResponseEntity.ok("patient assigned to clerk successfully");
    }

    @PutMapping("/clerk/updatePatient/{patientId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> updatePatientDetails(@PathVariable Long patientId, @RequestBody PatientDto patientDto, @RequestParam Long clerkId) 
    {    
    clerkService.updatePatientDetails(clerkId, patientId, patientDto);  
      return ResponseEntity.ok("Patient details updated successfully");
    }



}
