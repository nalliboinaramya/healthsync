package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ConsultationNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Consultation not found";
        ConsultationNotFoundException exception = new ConsultationNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}