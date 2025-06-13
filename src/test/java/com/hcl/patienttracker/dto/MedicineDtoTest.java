package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MedicineDtoTest {
    private MedicineDto medicineDto;

    @BeforeEach
    public void setUp() {
        medicineDto = new MedicineDto();
        medicineDto.setId(1L);
        medicineDto.setMedicineId("MED123");
        medicineDto.setName("Paracetamol");
        medicineDto.setManufacturer("Pharma Inc.");
        medicineDto.setPrice(50.0);
        medicineDto.setStock(100);
        medicineDto.setExpiryDate("2025-12-31");
    }

    @Test
    public void testMedicineDto() {
        Assertions.assertEquals(1L, medicineDto.getId());
        Assertions.assertEquals("MED123", medicineDto.getMedicineId());
        Assertions.assertEquals("Paracetamol", medicineDto.getName());
        Assertions.assertEquals("Pharma Inc.", medicineDto.getManufacturer());
        Assertions.assertEquals(50.0, medicineDto.getPrice());
        Assertions.assertEquals(100, medicineDto.getStock());
        Assertions.assertEquals("2025-12-31", medicineDto.getExpiryDate());
    }

    @Test
    public void testMedicineDtoWithNullValues() {
        MedicineDto nullMedicineDto = new MedicineDto();
        nullMedicineDto.setId(null);
        nullMedicineDto.setMedicineId(null);
        nullMedicineDto.setName(null);
        nullMedicineDto.setManufacturer(null);
        nullMedicineDto.setPrice(null);
        nullMedicineDto.setStock(null);
        nullMedicineDto.setExpiryDate(null);

        Assertions.assertNull(nullMedicineDto.getId());
        Assertions.assertNull(nullMedicineDto.getMedicineId());
        Assertions.assertNull(nullMedicineDto.getName());
        Assertions.assertNull(nullMedicineDto.getManufacturer());
        Assertions.assertNull(nullMedicineDto.getPrice());
        Assertions.assertNull(nullMedicineDto.getStock());
        Assertions.assertNull(nullMedicineDto.getExpiryDate());
    }
}