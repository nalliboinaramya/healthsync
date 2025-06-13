package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginRequestDtoTest {
    private LoginRequestDto loginRequestDto;

    @BeforeEach
    public void setUp() {
        loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("john.doe@example.com");
        loginRequestDto.setPassword("password123");
    }

    @Test
    public void testLoginRequestDto() {
        Assertions.assertEquals("john.doe@example.com", loginRequestDto.getEmail());
        Assertions.assertEquals("password123", loginRequestDto.getPassword());
    }

    @Test
    public void testLoginRequestDtoWithNullValues() {
        LoginRequestDto nullLoginRequestDto = new LoginRequestDto();
        nullLoginRequestDto.setEmail(null);
        nullLoginRequestDto.setPassword(null);

        Assertions.assertNull(nullLoginRequestDto.getEmail());
        Assertions.assertNull(nullLoginRequestDto.getPassword());
    }
}