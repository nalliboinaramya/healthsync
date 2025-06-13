package com.hcl.patienttracker.dto;

import com.hcl.patienttracker.entity.Prescription;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillingDtoTest {
    private BillingDto billingDto;
    private Prescription prescription;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription();

        billingDto = new BillingDto();
        billingDto.setId(1L);
//        billingDto.setPrescription(prescription);
        billingDto.setTotalCost(500.0);
    }

    @Test
    public void testBillingDto() {
        Assertions.assertEquals(1L, billingDto.getId());
      //  Assertions.assertEquals(prescription, billingDto.getPrescription());
        Assertions.assertEquals(500.0, billingDto.getTotalCost());
    }

    @Test
    public void testBillingDtoWithNullValues() {
        BillingDto nullBillingDto = new BillingDto();
        nullBillingDto.setId(null);
        nullBillingDto.setPrescription(null);
        nullBillingDto.setTotalCost(0.0);

        Assertions.assertNull(nullBillingDto.getId());
        Assertions.assertNull(nullBillingDto.getPrescription());
        Assertions.assertEquals(0.0, nullBillingDto.getTotalCost());
    }
}