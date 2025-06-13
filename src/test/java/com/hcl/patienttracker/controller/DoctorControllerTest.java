package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.DoctorDto;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.service.DoctorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorControllerTest {

    @Mock
    private DoctorService doctorService;

    @InjectMocks
    private DoctorController doctorController;

    private DoctorDto doctorDto;

    @BeforeEach
    public void setUp() {
        doctorDto = new DoctorDto();
        doctorDto.setDoctorId(1L);
        doctorDto.setFirstName("John");
        doctorDto.setLastName("Doe");
        doctorDto.setAge(45);
        doctorDto.setGender("Male");
        doctorDto.setContactNumber("1234567890");
        doctorDto.setSpecialization("Cardiology");
    }

    @Test
    public void testCreateDoctor() {
        when(doctorService.createDoctor(any(DoctorDto.class))).thenReturn(doctorDto);

        ResponseEntity<DoctorDto> response = doctorController.createDoctor(doctorDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(doctorDto, response.getBody());
        verify(doctorService, times(1)).createDoctor(any(DoctorDto.class));
    }

    @Test
    public void testGetDoctorById() {
        when(doctorService.getDoctorById(1L)).thenReturn(doctorDto);

        ResponseEntity<DoctorDto> response = doctorController.getDoctorById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctorDto, response.getBody());
        verify(doctorService, times(1)).getDoctorById(1L);
    }

    @Test
    public void testGetAllDoctors() {
        List<DoctorDto> doctorList = Arrays.asList(doctorDto);
        when(doctorService.getAllDoctors()).thenReturn(doctorList);

        ResponseEntity<List<DoctorDto>> response = doctorController.getAllDoctors();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctorList, response.getBody());
        verify(doctorService, times(1)).getAllDoctors();
    }

    @Test
    public void testUpdateDoctor() {
        when(doctorService.updateDoctor(1L, doctorDto)).thenReturn(doctorDto);

        ResponseEntity<DoctorDto> response = doctorController.updateDoctor(1L, doctorDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(doctorDto, response.getBody());
        verify(doctorService, times(1)).updateDoctor(1L, doctorDto);
    }

    @Test
    public void testDeleteDoctor() {
        doNothing().when(doctorService).deleteDoctor(1L);

        ResponseEntity<Void> response = doctorController.deleteDoctor(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(doctorService, times(1)).deleteDoctor(1L);
    }

    @Test
    public void testAssignPatientToDoctor() {
        when(doctorService.assignpatient(1L, 1L)).thenReturn(doctorDto);

        ResponseEntity<?> response = doctorController.assignPatientToDoctor(1L, 1L);

        assertNotNull(response);
        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(doctorDto, response.getBody());
        verify(doctorService, times(1)).assignpatient(1L, 1L);
    }
}
