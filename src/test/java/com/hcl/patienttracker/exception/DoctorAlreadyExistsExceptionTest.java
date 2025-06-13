package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class DoctorAlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Doctor already exists";
        DoctorAlreadyExistsException exception = new DoctorAlreadyExistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}