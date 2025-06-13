package com.hcl.patienttracker.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Date;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;
import com.hcl.patienttracker.filter.JwtAuthFilter;
import com.hcl.patienttracker.service.JwtService;
import com.hcl.patienttracker.service.PatientService;

@WebMvcTest(PatientController.class)
@AutoConfigureMockMvc(addFilters = false)
public class PatientCtrlrTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PatientService patientService;
	
	@MockBean
	private JwtService jwtService;
	
	@MockBean
	private JwtAuthFilter jwtAuthFilter;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	
	private Patient patient;
	private PatientDto patientDto;
	private PatientDto patientDto2;
	private PatientDto patientDtoUpdate;
	
	@BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setEmail("jane.doe@example.com");
        patient.setCity("Bengaluru");

        patientDto = new PatientDto();
        patientDto.setId(1);
        patientDto.setName("Jane Doe");
        patientDto.setDob(new Date());
        patientDto.setGender("Female");
        patientDto.setContactNumber("0987654321");
        patientDto.setEmail("jane.doe@example.com");
        patientDto.setCity("Bengaluru");
        
        patientDto2 = new PatientDto();
        patientDto2.setName("Jane Doe");
        patientDto2.setDob(new Date());
        patientDto2.setGender("Female");
        patientDto2.setContactNumber("0987654321");
        patientDto2.setEmail("jane.doe@example.com");
        patientDto2.setCity("Bengaluru");
        
        patientDtoUpdate = new PatientDto();
        patientDtoUpdate.setId(1);
        patientDtoUpdate.setName("Vishnu");
        patientDtoUpdate.setDob(new Date());
        patientDtoUpdate.setGender("Female");
        patientDtoUpdate.setContactNumber("0987654321");
        patientDtoUpdate.setEmail("jane.doe@example.com");
        patientDtoUpdate.setCity("Bengaluru");
        
       
    }
	
	@Test
	void testAddNewPatient() throws Exception{
		when(patientService.addNewPatient(any(PatientDto.class))).thenReturn(patientDto2);
		
		mockMvc.perform(post("/api/patients")
		.contentType(MediaType.APPLICATION_JSON)
        .content(objectMapper.writeValueAsString(patientDto2))).andExpect(status().isCreated()).andExpect(jsonPath("$.id").value(patientDto2.getId()));
		
	}
	
	@Test
	void testAddNewPatientException1() throws Exception {
	    when(patientService.addNewPatient(any(PatientDto.class)))
	        .thenThrow(new PatientAlreadyexistsException("patient already exists"));

	    mockMvc.perform(post("/api/patients")
	            .contentType(MediaType.APPLICATION_JSON)
	            .content(objectMapper.writeValueAsString(patientDto2)))
	           .andExpect(status().isBadRequest());
	}
	
	@Test
	void testGetPatientById() throws Exception {
		when(patientService.getPatient(1L)).thenReturn(patientDto);
		
		mockMvc.perform(get("/api/patients/{id}",1L)).andExpect(status().isOk())
		.andExpect(jsonPath("$.id").value(patientDto.getId()))
		.andExpect(jsonPath("$.name").value(patientDto.getName()));
	}
	
	
	@Test
	void testGetAllPatients() throws Exception{
		when(patientService.getAllPatients()).thenReturn(List.of(patientDto,patientDto2));
		
		mockMvc.perform(get("/api/patients"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$[1].name").value(patientDto2.getName()));
	}
	
	
	@Test
	void testDeletePatient() throws Exception{
		
		doNothing().when(patientService).deletePatient(1L);
		
		mockMvc.perform(delete("/api/patients/{id}",1L)).andExpect(status().isOk()).andExpect(content().string("Delete sucessfully"));
	}
	
	
	

}
