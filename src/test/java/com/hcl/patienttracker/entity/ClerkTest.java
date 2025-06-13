package com.hcl.patienttracker.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ClerkTest {
    private Clerk clerk;

    @BeforeEach
    public void setUp() {
        clerk = new Clerk();
        clerk.setId(1L);
        clerk.setFirstName("John");
        clerk.setLastName("Doe");
        clerk.setAge(25);
        clerk.setContactNumber("9876543210");
        clerk.setGender("Male");
    }

    @Test
    public void testClerkEntity() {
        Assertions.assertEquals(1L, clerk.getId());
        Assertions.assertEquals("John", clerk.getFirstName());
        Assertions.assertEquals("Doe", clerk.getLastName());
        Assertions.assertEquals(25, clerk.getAge());
        Assertions.assertEquals("9876543210", clerk.getContactNumber());
        Assertions.assertEquals("Male", clerk.getGender());
    }

    @Test
    public void testClerkEntityWithNullValues() {
        Clerk nullClerk = new Clerk();
        nullClerk.setId(null);
        nullClerk.setFirstName(null);
        nullClerk.setLastName(null);
        nullClerk.setAge(0);
        nullClerk.setContactNumber(null);
        nullClerk.setGender(null);

        Assertions.assertNull(nullClerk.getId());
        Assertions.assertNull(nullClerk.getFirstName());
        Assertions.assertNull(nullClerk.getLastName());
        Assertions.assertEquals(0, nullClerk.getAge());
        Assertions.assertNull(nullClerk.getContactNumber());
        Assertions.assertNull(nullClerk.getGender());
    }
}