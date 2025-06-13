package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClerkAlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Clerk already exists";
        ClerkAlreadyExistsException exception = new ClerkAlreadyExistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}