package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PrescriptionMedicineTest {
    private PrescriptionMedicine prescriptionMedicine;
    private Prescription prescription;
    private Medicine medicine;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription();
        medicine = new Medicine();

        prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setId(1L);
        prescriptionMedicine.setPrescription(prescription);
        prescriptionMedicine.setMedicine(medicine);
        prescriptionMedicine.setPrescribedQuantity(2);
        prescriptionMedicine.setDosage(500);
        prescriptionMedicine.setPrice(100.0);
    }

    @Test
    public void testPrescriptionMedicineEntity() {
        Assertions.assertEquals(1L, prescriptionMedicine.getId());
        Assertions.assertEquals(prescription, prescriptionMedicine.getPrescription());
        Assertions.assertEquals(medicine, prescriptionMedicine.getMedicine());
        Assertions.assertEquals(2, prescriptionMedicine.getPrescribedQuantity());
        Assertions.assertEquals(500, prescriptionMedicine.getDosage());
        Assertions.assertEquals(100.0, prescriptionMedicine.getPrice());
    }

    @Test
    public void testPrescriptionMedicineEntityWithNullValues() {
        PrescriptionMedicine nullPrescriptionMedicine = new PrescriptionMedicine();
        nullPrescriptionMedicine.setId(null);
        nullPrescriptionMedicine.setPrescription(null);
        nullPrescriptionMedicine.setMedicine(null);
        nullPrescriptionMedicine.setPrescribedQuantity(null);
        nullPrescriptionMedicine.setDosage(null);
        nullPrescriptionMedicine.setPrice(null);

        Assertions.assertNull(nullPrescriptionMedicine.getId());
        Assertions.assertNull(nullPrescriptionMedicine.getPrescription());
        Assertions.assertNull(nullPrescriptionMedicine.getMedicine());
        Assertions.assertNull(nullPrescriptionMedicine.getPrescribedQuantity());
        Assertions.assertNull(nullPrescriptionMedicine.getDosage());
        Assertions.assertNull(nullPrescriptionMedicine.getPrice());
    }
}