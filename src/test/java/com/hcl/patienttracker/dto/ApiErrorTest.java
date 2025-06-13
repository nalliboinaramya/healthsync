package com.hcl.patienttracker.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

public class ApiErrorTest {
    private ApiError apiError;
    private List<String> subErrors;

    @BeforeEach
    public void setUp() {
        apiError = new ApiError();
        subErrors = Arrays.asList("Error1", "Error2");
    }

    @Test
    public void testApiError() {
        apiError.setStatus(HttpStatus.BAD_REQUEST);
        apiError.setStatusCode(400);
        apiError.setMessage("Bad Request");
        apiError.setSubError(subErrors);

        Assertions.assertEquals(HttpStatus.BAD_REQUEST, apiError.getStatus());
        Assertions.assertEquals(400, apiError.getStatusCode());
        Assertions.assertEquals("Bad Request", apiError.getMessage());
        Assertions.assertEquals(subErrors, apiError.getSubError());
    }

    @Test
    public void testApiErrorWithNullValues() {
        ApiError nullApiError = new ApiError();
        nullApiError.setStatus(null);
        nullApiError.setStatusCode(0);
        nullApiError.setMessage(null);
        nullApiError.setSubError(null);

        Assertions.assertNull(nullApiError.getStatus());
        Assertions.assertEquals(0, nullApiError.getStatusCode());
        Assertions.assertNull(nullApiError.getMessage());
        Assertions.assertNull(nullApiError.getSubError());
    }
}