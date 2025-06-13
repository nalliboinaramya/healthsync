package com.hcl.patienttracker.exception;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AdminNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Admin not found";
        AdminNotFoundException exception = new AdminNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}