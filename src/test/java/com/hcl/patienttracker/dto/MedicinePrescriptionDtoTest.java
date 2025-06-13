package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class MedicinePrescriptionDtoTest {
    private MedicinePrescriptionRequestDto medicinePrescriptionDto;
    private Map<Long, Integer> medicineDoseMap;

    @BeforeEach
    public void setUp() {
        medicineDoseMap = new HashMap<>();
        medicineDoseMap.put(1L, 2);

        medicinePrescriptionDto = new MedicinePrescriptionRequestDto();
        medicinePrescriptionDto.setPatientId(1L);
        medicinePrescriptionDto.setDoctorId(1L);
        medicinePrescriptionDto.setMedicineDoseMap(medicineDoseMap);
    }

    @Test
    public void testMedicinePrescriptionDto() {
        Assertions.assertEquals(1L, medicinePrescriptionDto.getPatientId());
        Assertions.assertEquals(1L, medicinePrescriptionDto.getDoctorId());
        Assertions.assertEquals(medicineDoseMap, medicinePrescriptionDto.getMedicineDoseMap());
    }

    @Test
    public void testMedicinePrescriptionDtoWithNullValues() {
        MedicinePrescriptionRequestDto nullMedicinePrescriptionDto = new MedicinePrescriptionRequestDto();
        nullMedicinePrescriptionDto.setPatientId(null);
        nullMedicinePrescriptionDto.setDoctorId(null);
        nullMedicinePrescriptionDto.setMedicineDoseMap(null);

        Assertions.assertNull(nullMedicinePrescriptionDto.getPatientId());
        Assertions.assertNull(nullMedicinePrescriptionDto.getDoctorId());
        Assertions.assertNull(nullMedicinePrescriptionDto.getMedicineDoseMap());
    }
}