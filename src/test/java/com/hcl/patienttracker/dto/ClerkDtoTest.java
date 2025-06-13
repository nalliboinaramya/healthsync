package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClerkDtoTest {
    private ClerkDto clerkDto;

    @BeforeEach
    public void setUp() {
        clerkDto = new ClerkDto();
        clerkDto.setFirstName("John");
        clerkDto.setLastName("Doe");
        clerkDto.setAge(30);
        clerkDto.setContactNumber("9876543210");
        clerkDto.setGender("Male");
    }

    @Test
    public void testClerkDto() {
        Assertions.assertEquals("John", clerkDto.getFirstName());
        Assertions.assertEquals("Doe", clerkDto.getLastName());
        Assertions.assertEquals(30, clerkDto.getAge());
        Assertions.assertEquals("9876543210", clerkDto.getContactNumber());
        Assertions.assertEquals("Male", clerkDto.getGender());
    }

    @Test
    public void testClerkDtoWithNullValues() {
        ClerkDto nullClerkDto = new ClerkDto();
        nullClerkDto.setFirstName(null);
        nullClerkDto.setLastName(null);
        nullClerkDto.setAge(0);
        nullClerkDto.setContactNumber(null);
        nullClerkDto.setGender(null);

        Assertions.assertNull(nullClerkDto.getFirstName());
        Assertions.assertNull(nullClerkDto.getLastName());
        Assertions.assertEquals(0, nullClerkDto.getAge());
        Assertions.assertNull(nullClerkDto.getContactNumber());
        Assertions.assertNull(nullClerkDto.getGender());
    }
}