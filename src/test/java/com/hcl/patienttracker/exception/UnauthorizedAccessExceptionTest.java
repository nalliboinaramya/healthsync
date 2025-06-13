package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnauthorizedAccessExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Unauthorized access";
        UnauthorizedAccessException exception = new UnauthorizedAccessException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}