package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Patient not found";
        PatientNotFoundException exception = new PatientNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}