package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

public class ExceptionResponseDtoTest {
    private ExceptionResponseDto exceptionResponseDto;

    @BeforeEach
    public void setUp() {
        exceptionResponseDto = new ExceptionResponseDto("ExceptionName", "ExceptionMessage", HttpStatus.BAD_REQUEST);
    }

    @Test
    public void testExceptionResponseDto() {
        Assertions.assertEquals("ExceptionName", exceptionResponseDto.getName());
        Assertions.assertEquals("ExceptionMessage", exceptionResponseDto.getMessage());
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, exceptionResponseDto.getStatus());
    }

    @Test
    public void testExceptionResponseDtoWithNullValues() {
        ExceptionResponseDto nullExceptionResponseDto = new ExceptionResponseDto(null, null, null);

        Assertions.assertNull(nullExceptionResponseDto.getName());
        Assertions.assertNull(nullExceptionResponseDto.getMessage());
        Assertions.assertNull(nullExceptionResponseDto.getStatus());
    }
}