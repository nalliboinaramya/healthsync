package com.hcl.patienttracker.exception;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminAlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Admin already exists";
        AdminAlreadyExistsException exception = new AdminAlreadyExistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}