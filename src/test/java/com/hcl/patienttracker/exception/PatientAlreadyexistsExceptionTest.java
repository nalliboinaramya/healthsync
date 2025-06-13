package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PatientAlreadyexistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Patient already exists";
        PatientAlreadyexistsException exception = new PatientAlreadyexistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}