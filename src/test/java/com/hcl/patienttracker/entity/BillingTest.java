package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BillingTest {
    private Billing billing;
    private Prescription prescription;
    private Patient patient;

    @BeforeEach
    public void setUp() {
        prescription = new Prescription();
        patient = new Patient();

        billing = new Billing();
        billing.setId(1L);
        //billing.setPrescription(prescription);
        //billing.setPatient(patient);
        billing.setTotalCost(500.0);
    }

    @Test
    public void testBillingEntity() {
        Assertions.assertEquals(1L, billing.getId());
        //Assertions.assertEquals(prescription, billing.getPrescription());
        //Assertions.assertEquals(patient, billing.getPatient());
        Assertions.assertEquals(500.0, billing.getTotalCost());
    }

    @Test
    public void testBillingEntityWithNullValues() {
        Billing nullBilling = new Billing();
        nullBilling.setId(null);
        //nullBilling.setPrescription(null);
        //nullBilling.setPatient(null);
        nullBilling.setTotalCost(0.0);

        Assertions.assertNull(nullBilling.getId());
        //Assertions.assertNull(nullBilling.getPrescription());
        //Assertions.assertNull(nullBilling.getPatient());
        Assertions.assertEquals(0.0, nullBilling.getTotalCost());
    }
}