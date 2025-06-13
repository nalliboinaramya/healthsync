package com.hcl.patienttracker.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MedicineOutOfStockExceptionTest {

    @Test
    public void testExceptionMessage() {
        String message = "Medicine out of stock";
        MedicineOutOfStockException exception = new MedicineOutOfStockException(message);

        Assertions.assertEquals(message, exception.getMessage());
    }
}