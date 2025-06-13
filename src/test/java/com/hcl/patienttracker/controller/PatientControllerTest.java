package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;
import com.hcl.patienttracker.service.PatientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientControllerTest {

    @Mock
    private PatientService patientService;

    @InjectMocks
    private PatientController patientController;

    private PatientDto patientDto;
    

    @BeforeEach
    public void setUp() {
        patientDto = new PatientDto();
        patientDto.setId(1);
        patientDto.setName("Jane Doe");
        patientDto.setDob(new Date());
        patientDto.setGender("Female");
        patientDto.setContactNumber("0987654321");
        patientDto.setEmail("jane.doe@example.com");
        patientDto.setCity("Bengaluru");
        
       
    
    }

//    @Test
//    public void testAddNewPatient() throws PatientAlreadyexistsException {
//        when(patientService.addNewPatient(any(RequestPatientPayloadDto.class))).thenReturn(patientDto);
//
//        ResponseEntity<?> response = patientController.addNewPatient(patientPayloadDto);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.CREATED, response.getStatusCode());
//        assertEquals(patientDto, response.getBody());
//        verify(patientService, times(1)).addNewPatient(any(RequestPatientPayloadDto.class));
//    }


    @Test
    public void testGetAllPatients() {
        List<PatientDto> patientList = Arrays.asList(patientDto);
        when(patientService.getAllPatients()).thenReturn(patientList);

        ResponseEntity<List<PatientDto>> response = patientController.getAllPatients();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientList, response.getBody());
        verify(patientService, times(1)).getAllPatients();
    }

    @Test
    public void testUpdatePatientDetails() {
        when(patientService.updatePatientDto(1L, patientDto)).thenReturn(patientDto);

        ResponseEntity<?> response = patientController.updatePatientDetails(1L, patientDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(patientDto, response.getBody());
        verify(patientService, times(1)).updatePatientDto(1L, patientDto);
    }

    @Test
    public void testDeletePatient() {
        doNothing().when(patientService).deletePatient(1L);

        ResponseEntity<?> response = patientController.deletePatient(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals("Delete successfully", response.getBody());
        verify(patientService, times(1)).deletePatient(1L);
    }

    
}