package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Doctor not found";
        DoctorNotFoundException exception = new DoctorNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}