package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionTest {
    private Prescription prescription;
    private Doctor doctor;
    private Patient patient;
    private Billing billing;
    private List<PrescriptionMedicine> prescriptionMedicines;

    @BeforeEach
    public void setUp() {
        doctor = new Doctor();
        patient = new Patient();
        billing = new Billing();
        prescriptionMedicines = new ArrayList<>();

        prescription = new Prescription();
        prescription.setPrescriptionId(1);
        prescription.setDate("2025-02-22");
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
     //   prescription.setBilling(billing);
        prescription.setPrescriptionMedicines(prescriptionMedicines);
    }

    @Test
    public void testPrescriptionEntity() {
        Assertions.assertEquals(1, prescription.getPrescriptionId());
        Assertions.assertEquals("2025-02-22", prescription.getDate());
        Assertions.assertEquals(doctor, prescription.getDoctor());
        Assertions.assertEquals(patient, prescription.getPatient());
      //  Assertions.assertEquals(billing, prescription.getBilling());
        Assertions.assertEquals(prescriptionMedicines, prescription.getPrescriptionMedicines());
    }

    @Test
    public void testPrescriptionEntityWithNullValues() {
        Prescription nullPrescription = new Prescription();
        nullPrescription.setPrescriptionId(null);
        nullPrescription.setDate(null);
        nullPrescription.setDoctor(null);
        nullPrescription.setPatient(null);
    //    nullPrescription.setBilling(null);
        nullPrescription.setPrescriptionMedicines(null);

        Assertions.assertNull(nullPrescription.getPrescriptionId());
        Assertions.assertNull(nullPrescription.getDate());
        Assertions.assertNull(nullPrescription.getDoctor());
        Assertions.assertNull(nullPrescription.getPatient());
       // Assertions.assertNull(nullPrescription.getBilling());
        Assertions.assertNull(nullPrescription.getPrescriptionMedicines());
    }
}