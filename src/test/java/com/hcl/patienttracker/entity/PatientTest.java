package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PatientTest {
    private Patient patient;
    private List<Doctor> doctors;
    private List<Prescription> prescriptions;
    private List<Billing> bills;

    @BeforeEach
    public void setUp() {
        doctors = new ArrayList<>();
        prescriptions = new ArrayList<>();
        bills = new ArrayList<>();

        patient = new Patient();
        patient.setId(1L);
        patient.setName("John Doe");
        patient.setDob(new Date(1990, 1, 1));
        patient.setGender("Male");
        patient.setContactNumber("9876543210");
        patient.setEmail("john.doe@example.com");
        patient.setCity("New York"); // Set the city field
        patient.setDoctors(doctors);
        patient.setPrescriptions(prescriptions);
        patient.setBills(bills);
    }

    @Test
    public void testPatientEntity() {
        Assertions.assertEquals(1L, patient.getId());
        Assertions.assertEquals("John Doe", patient.getName());
        Assertions.assertEquals(new Date(1990, 1, 1), patient.getDob());
        Assertions.assertEquals("Male", patient.getGender());
        Assertions.assertEquals("9876543210", patient.getContactNumber());
        Assertions.assertEquals("john.doe@example.com", patient.getEmail());
        Assertions.assertEquals("New York", patient.getCity()); // Verify the city field
        Assertions.assertEquals(doctors, patient.getDoctors());
        Assertions.assertEquals(prescriptions, patient.getPrescriptions());
        Assertions.assertEquals(bills, patient.getBills());
    }

    @Test
    public void testPatientEntityWithNullValues() {
        Patient nullPatient = new Patient();
        nullPatient.setId(null);
        nullPatient.setName(null);
        nullPatient.setDob(null);
        nullPatient.setGender(null);
        nullPatient.setContactNumber(null);
        nullPatient.setEmail(null);
        nullPatient.setCity(null);
        nullPatient.setDoctors(null);
        nullPatient.setPrescriptions(null);
        nullPatient.setBills(null);

        Assertions.assertNull(nullPatient.getId());
        Assertions.assertNull(nullPatient.getName());
        Assertions.assertNull(nullPatient.getDob());
        Assertions.assertNull(nullPatient.getGender());
        Assertions.assertNull(nullPatient.getContactNumber());
        Assertions.assertNull(nullPatient.getEmail());
        Assertions.assertNull(nullPatient.getCity());
        Assertions.assertNull(nullPatient.getDoctors());
        Assertions.assertNull(nullPatient.getPrescriptions());
        Assertions.assertNull(nullPatient.getBills());
    }
}