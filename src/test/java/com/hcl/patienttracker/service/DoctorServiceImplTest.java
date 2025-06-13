package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.DoctorDto;
import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.repository.DoctorRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.impl.DoctorServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DoctorServiceImplTest {
    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private DoctorServiceImpl doctorService;

    private Doctor doctor;
    private DoctorDto doctorDto;
    private Patient patient;

    @BeforeEach
    void setUp() {
        doctor = new Doctor();
        doctor.setDoctorId(1L);
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setAge(45);
        doctor.setGender("Male");
        doctor.setContactNumber("1234567890");
        doctor.setSpecialization("Cardiology");

        doctorDto = new DoctorDto();
        doctorDto.setDoctorId(1L);
        doctorDto.setFirstName("John");
        doctorDto.setLastName("Doe");
        doctorDto.setAge(45);
        doctorDto.setGender("Male");
        doctorDto.setContactNumber("1234567890");
        doctorDto.setSpecialization("Cardiology");

        patient = new Patient();
        patient.setId(1L);
        patient.setName("Jane Doe");
        patient.setDob(new Date());
        patient.setGender("Female");
        patient.setContactNumber("0987654321");
        patient.setEmail("jane.doe@example.com");
        patient.setCity("Bengaluru");
    }


    @Test
    void testDeleteDoctor() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));

        doctorService.deleteDoctor(1L);

        verify(doctorRepository, times(1)).findById(1L);
        verify(doctorRepository, times(1)).delete(any(Doctor.class));
    }

 

    @Test
    void testAssignPatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);
        when(modelMapper.map(any(Doctor.class), eq(DoctorDto.class))).thenReturn(doctorDto);

        DoctorDto assignedDoctor = doctorService.assignpatient(1L, 1L);

        assertNotNull(assignedDoctor);
        assertTrue(doctor.getPatients().contains(patient));
        verify(patientRepository, times(1)).findById(1L);
        verify(doctorRepository, times(1)).findById(1L);
        verify(doctorRepository, times(1)).save(any(Doctor.class));
    }

 

   
}
