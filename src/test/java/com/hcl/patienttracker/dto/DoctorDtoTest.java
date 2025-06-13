package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class DoctorDtoTest {
    private DoctorDto doctorDto;

    @BeforeEach
    public void setUp() {
        doctorDto = new DoctorDto();
        doctorDto.setDoctorId(1L);
        doctorDto.setFirstName("Alice");
        doctorDto.setLastName("Smith");
        doctorDto.setAge(35);
        doctorDto.setGender("Female");
        doctorDto.setContactNumber("1234567890");
        doctorDto.setSpecialization("Cardiology");
    }

    @Test
    public void testDoctorDto() {
        Assertions.assertEquals(1L, doctorDto.getDoctorId());
        Assertions.assertEquals("Alice", doctorDto.getFirstName());
        Assertions.assertEquals("Smith", doctorDto.getLastName());
        Assertions.assertEquals(35, doctorDto.getAge());
        Assertions.assertEquals("Female", doctorDto.getGender());
        Assertions.assertEquals("1234567890", doctorDto.getContactNumber());
        Assertions.assertEquals("Cardiology", doctorDto.getSpecialization());
    }

    @Test
    public void testDoctorDtoWithNullValues() {
        DoctorDto nullDoctorDto = new DoctorDto();
        nullDoctorDto.setDoctorId(null);
        nullDoctorDto.setFirstName(null);
        nullDoctorDto.setLastName(null);
        nullDoctorDto.setAge(0);
        nullDoctorDto.setGender(null);
        nullDoctorDto.setContactNumber(null);
        nullDoctorDto.setSpecialization(null);

        Assertions.assertNull(nullDoctorDto.getDoctorId());
        Assertions.assertNull(nullDoctorDto.getFirstName());
        Assertions.assertNull(nullDoctorDto.getLastName());
        Assertions.assertEquals(0, nullDoctorDto.getAge());
        Assertions.assertNull(nullDoctorDto.getGender());
        Assertions.assertNull(nullDoctorDto.getContactNumber());
        Assertions.assertNull(nullDoctorDto.getSpecialization());
    }
}