package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PrescriptionNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Prescription not found";
        PrescriptionNotFoundException exception = new PrescriptionNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}