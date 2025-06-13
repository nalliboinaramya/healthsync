package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ClerkNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Clerk not found";
        ClerkNotFoundException exception = new ClerkNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}