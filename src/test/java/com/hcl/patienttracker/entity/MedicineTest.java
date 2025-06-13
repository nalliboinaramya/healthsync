package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class MedicineTest {
    private Medicine medicine;
    private List<PrescriptionMedicine> prescriptionMedicines;

    @BeforeEach
    public void setUp() {
        prescriptionMedicines = new ArrayList<>();

        medicine = new Medicine();
        medicine.setId(1L);
        medicine.setMedicineId("MED123");
        medicine.setName("Paracetamol");
        medicine.setManufacturer("Pharma Inc.");
        medicine.setPrice(50.0);
        medicine.setStock(100);
        medicine.setExpiryDate("2025-12-31");
        medicine.setPrescriptionMedicines(prescriptionMedicines);
    }

    @Test
    public void testMedicineEntity() {
        Assertions.assertEquals(1L, medicine.getId());
        Assertions.assertEquals("MED123", medicine.getMedicineId());
        Assertions.assertEquals("Paracetamol", medicine.getName());
        Assertions.assertEquals("Pharma Inc.", medicine.getManufacturer());
        Assertions.assertEquals(50.0, medicine.getPrice());
        Assertions.assertEquals(100, medicine.getStock());
        Assertions.assertEquals("2025-12-31", medicine.getExpiryDate());
        Assertions.assertEquals(prescriptionMedicines, medicine.getPrescriptionMedicines());
    }

    @Test
    public void testMedicineEntityWithNullValues() {
        Medicine nullMedicine = new Medicine();
        nullMedicine.setId(null);
        nullMedicine.setMedicineId(null);
        nullMedicine.setName(null);
        nullMedicine.setManufacturer(null);
        nullMedicine.setPrice(null);
        nullMedicine.setStock(null);
        nullMedicine.setExpiryDate(null);
        nullMedicine.setPrescriptionMedicines(null);

        Assertions.assertNull(nullMedicine.getId());
        Assertions.assertNull(nullMedicine.getMedicineId());
        Assertions.assertNull(nullMedicine.getName());
        Assertions.assertNull(nullMedicine.getManufacturer());
        Assertions.assertNull(nullMedicine.getPrice());
        Assertions.assertNull(nullMedicine.getStock());
        Assertions.assertNull(nullMedicine.getExpiryDate());
        Assertions.assertNull(nullMedicine.getPrescriptionMedicines());
    }
}