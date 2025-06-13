package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LoginResponseDtoTest {
    private LoginResponseDto loginResponseDto;

    @BeforeEach
    public void setUp() {
        loginResponseDto = new LoginResponseDto();
        loginResponseDto.setType("Bearer");
        loginResponseDto.setToken("token123");
    }

    @Test
    public void testLoginResponseDto() {
        Assertions.assertEquals("Bearer", loginResponseDto.getType());
        Assertions.assertEquals("token123", loginResponseDto.getToken());
    }

    @Test
    public void testLoginResponseDtoWithNullValues() {
        LoginResponseDto nullLoginResponseDto = new LoginResponseDto();
        nullLoginResponseDto.setType(null);
        nullLoginResponseDto.setToken(null);

        Assertions.assertNull(nullLoginResponseDto.getType());
        Assertions.assertNull(nullLoginResponseDto.getToken());
    }
}