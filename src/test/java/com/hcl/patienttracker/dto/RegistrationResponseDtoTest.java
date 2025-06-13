package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationResponseDtoTest {
    private RegistrationResponseDto registrationResponseDto;

    @BeforeEach
    public void setUp() {
        registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setId(1L);
        registrationResponseDto.setFirstName("John");
        registrationResponseDto.setLastName("Doe");
        registrationResponseDto.setAge(30);
        registrationResponseDto.setGender("Male");
        registrationResponseDto.setRole("Admin");
        registrationResponseDto.setEmail("john.doe@example.com");
        registrationResponseDto.setContactNumber("9876543210");
        registrationResponseDto.setAdminId("admin123");
    }

    @Test
    public void testRegistrationResponseDto() {
        Assertions.assertEquals(1L, registrationResponseDto.getId());
        Assertions.assertEquals("John", registrationResponseDto.getFirstName());
        Assertions.assertEquals("Doe", registrationResponseDto.getLastName());
        Assertions.assertEquals(30, registrationResponseDto.getAge());
        Assertions.assertEquals("Male", registrationResponseDto.getGender());
        Assertions.assertEquals("Admin", registrationResponseDto.getRole());
        Assertions.assertEquals("john.doe@example.com", registrationResponseDto.getEmail());
        Assertions.assertEquals("9876543210", registrationResponseDto.getContactNumber());
        Assertions.assertEquals("admin123", registrationResponseDto.getAdminId());
    }

    @Test
    public void testRegistrationResponseDtoWithNullValues() {
        RegistrationResponseDto nullDto = new RegistrationResponseDto();
        nullDto.setId(null);
        nullDto.setFirstName(null);
        nullDto.setLastName(null);
        nullDto.setAge(null);
        nullDto.setGender(null);
        nullDto.setRole(null);
        nullDto.setEmail(null);
        nullDto.setContactNumber(null);
        nullDto.setAdminId(null);

        Assertions.assertNull(nullDto.getId());
        Assertions.assertNull(nullDto.getFirstName());
        Assertions.assertNull(nullDto.getLastName());
        Assertions.assertNull(nullDto.getAge());
        Assertions.assertNull(nullDto.getGender());
        Assertions.assertNull(nullDto.getRole());
        Assertions.assertNull(nullDto.getEmail());
        Assertions.assertNull(nullDto.getContactNumber());
        Assertions.assertNull(nullDto.getAdminId());
    }
}