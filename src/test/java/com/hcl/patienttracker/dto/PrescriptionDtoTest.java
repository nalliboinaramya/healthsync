package com.hcl.patienttracker.dto;

import com.hcl.patienttracker.entity.PrescriptionMedicine;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class PrescriptionDtoTest {

    private PrescriptionDto prescriptionDto;

    @BeforeEach
    public void setUp() {
        prescriptionDto = new PrescriptionDto();
        prescriptionDto.setPrescriptionId(1);
        prescriptionDto.setDate("2025-03-04");

        List<PrescriptionMedicine> prescriptionMedicines = new ArrayList<>();
        prescriptionDto.setPrescriptionMedicines(prescriptionMedicines);
    }

    @Test
    public void testPrescriptionDto() {
        Assertions.assertEquals(1, prescriptionDto.getPrescriptionId());
        Assertions.assertEquals("2025-03-04", prescriptionDto.getDate());
        Assertions.assertNotNull(prescriptionDto.getPrescriptionMedicines());
    }
}