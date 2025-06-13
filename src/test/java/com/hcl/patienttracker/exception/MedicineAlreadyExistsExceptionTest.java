package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicineAlreadyExistsExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Medicine already exists";
        MedicineAlreadyExistsException exception = new MedicineAlreadyExistsException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}