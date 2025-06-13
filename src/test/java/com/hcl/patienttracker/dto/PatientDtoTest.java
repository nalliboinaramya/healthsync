package com.hcl.patienttracker.dto;

import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Prescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientDtoTest {
    private PatientDto patientDto;
    private List<Doctor> doctors;
    private List<Prescription> prescriptions;

    @BeforeEach
    public void setUp() {
        doctors = new ArrayList<>();
        prescriptions = new ArrayList<>();

        patientDto = new PatientDto();
        patientDto.setId(1);
        patientDto.setName("John Doe");
        patientDto.setDob(new Date(1990, 1, 1));
        patientDto.setGender("Male");
        patientDto.setContactNumber("9876543210");
        patientDto.setEmail("john.doe@example.com");
        patientDto.setCity("New York");
//        patientDto.setDoctors(doctors);
//        patientDto.setPrescriptions(prescriptions);
    }

    @Test
    public void testPatientDto() {
        Assertions.assertEquals(1, patientDto.getId());
        Assertions.assertEquals("John Doe", patientDto.getName());
        Assertions.assertEquals(new Date(1990, 1, 1), patientDto.getDob());
        Assertions.assertEquals("Male", patientDto.getGender());
        Assertions.assertEquals("9876543210", patientDto.getContactNumber());
        Assertions.assertEquals("john.doe@example.com", patientDto.getEmail());
        Assertions.assertEquals("New York", patientDto.getCity());
//        Assertions.assertEquals(doctors, patientDto.getDoctors());
//        Assertions.assertEquals(prescriptions, patientDto.getPrescriptions());
    }

    @Test
    public void testPatientDtoWithNullValues() {
        PatientDto nullPatientDto = new PatientDto();
        nullPatientDto.setId(null);
        nullPatientDto.setName(null);
        nullPatientDto.setDob(null);
        nullPatientDto.setGender(null);
        nullPatientDto.setContactNumber(null);
        nullPatientDto.setEmail(null);
        nullPatientDto.setCity(null);
        nullPatientDto.setDoctors(null);
        nullPatientDto.setPrescriptions(null);

        Assertions.assertNull(nullPatientDto.getId());
        Assertions.assertNull(nullPatientDto.getName());
        Assertions.assertNull(nullPatientDto.getDob());
        Assertions.assertNull(nullPatientDto.getGender());
        Assertions.assertNull(nullPatientDto.getContactNumber());
        Assertions.assertNull(nullPatientDto.getEmail());
        Assertions.assertNull(nullPatientDto.getCity());
        Assertions.assertNull(nullPatientDto.getDoctors());
        Assertions.assertNull(nullPatientDto.getPrescriptions());
    }
}