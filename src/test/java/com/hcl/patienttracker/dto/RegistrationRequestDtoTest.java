package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegistrationRequestDtoTest {
    private RegistrationRequestDto registrationRequestDto;

    @BeforeEach
    public void setUp() {
        registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setId(1L);
        registrationRequestDto.setFirstName("John");
        registrationRequestDto.setLastName("Doe");
        registrationRequestDto.setAge(30);
        registrationRequestDto.setGender("Male");
        registrationRequestDto.setRole("Admin");
        registrationRequestDto.setEmail("john.doe@example.com");
        registrationRequestDto.setContactNumber("9876543210");
        registrationRequestDto.setAdminId("admin123");
        registrationRequestDto.setPassword("password123");
    }

    @Test
    public void testRegistrationRequestDto() {
        Assertions.assertEquals(1L, registrationRequestDto.getId());
        Assertions.assertEquals("John", registrationRequestDto.getFirstName());
        Assertions.assertEquals("Doe", registrationRequestDto.getLastName());
        Assertions.assertEquals(30, registrationRequestDto.getAge());
        Assertions.assertEquals("Male", registrationRequestDto.getGender());
        Assertions.assertEquals("Admin", registrationRequestDto.getRole());
        Assertions.assertEquals("john.doe@example.com", registrationRequestDto.getEmail());
        Assertions.assertEquals("9876543210", registrationRequestDto.getContactNumber());
        Assertions.assertEquals("admin123", registrationRequestDto.getAdminId());
        Assertions.assertEquals("password123", registrationRequestDto.getPassword());
    }

    @Test
    public void testRegistrationRequestDtoWithNullValues() {
        RegistrationRequestDto nullDto = new RegistrationRequestDto();
        nullDto.setId(null);
        nullDto.setFirstName(null);
        nullDto.setLastName(null);
        nullDto.setAge(null);
        nullDto.setGender(null);
        nullDto.setRole(null);
        nullDto.setEmail(null);
        nullDto.setContactNumber(null);
        nullDto.setAdminId(null);
        nullDto.setPassword(null);

        Assertions.assertNull(nullDto.getId());
        Assertions.assertNull(nullDto.getFirstName());
        Assertions.assertNull(nullDto.getLastName());
        Assertions.assertNull(nullDto.getAge());
        Assertions.assertNull(nullDto.getGender());
        Assertions.assertNull(nullDto.getRole());
        Assertions.assertNull(nullDto.getEmail());
        Assertions.assertNull(nullDto.getContactNumber());
        Assertions.assertNull(nullDto.getAdminId());
        Assertions.assertNull(nullDto.getPassword());
    }
}