package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class DoctorTest {

    private Doctor doctor;

    @BeforeEach
    public void setUp() {
        doctor = new Doctor();
        doctor.setDoctorId(1L);
        doctor.setFirstName("John");
        doctor.setLastName("Doe");
        doctor.setAge(30);
        doctor.setGender("Male");
        doctor.setContactNumber("1234567890");
        doctor.setSpecialization("Cardiology");

        List<Patient> patients = new ArrayList<>();
        doctor.setPatients(patients);

        List<Prescription> prescriptions = new ArrayList<>();
        doctor.setPrescriptions(prescriptions);
    }

    @Test
    public void testDoctorEntity() {
        Assertions.assertEquals(1L, doctor.getDoctorId());
        Assertions.assertEquals("John", doctor.getFirstName());
        Assertions.assertEquals("Doe", doctor.getLastName());
        Assertions.assertEquals(30, doctor.getAge());
        Assertions.assertEquals("Male", doctor.getGender());
        Assertions.assertEquals("1234567890", doctor.getContactNumber());
        Assertions.assertEquals("Cardiology", doctor.getSpecialization());
        Assertions.assertNotNull(doctor.getPatients());
        Assertions.assertNotNull(doctor.getPrescriptions());
    }
}