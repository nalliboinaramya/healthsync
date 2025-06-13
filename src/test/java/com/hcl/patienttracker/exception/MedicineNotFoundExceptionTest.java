package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicineNotFoundExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Medicine not found";
        MedicineNotFoundException exception = new MedicineNotFoundException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}