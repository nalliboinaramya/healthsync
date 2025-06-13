package com.hcl.patienttracker.entity;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class AdminTest {
    private Admin admin;

    @BeforeEach
    public void setUp()
    {
        admin = new Admin();
        admin.setId(1L);
        admin.setFirstName("Prasad");
        admin.setLastName("Vagger");
        admin.setAge(30);
        admin.setRole("ADMIN");
        admin.setGender("Male");
        admin.setContactNumber("1234567890");
        admin.setAdminId("admin123");
        admin.setPassword("Prasad@123");
        admin.setEmail("prasad.vagger@gmail.com");
    }
    @Test
    public void testAdminEntity()
    {
        Assertions.assertEquals(1L, admin.getId());
        Assertions.assertEquals("Prasad", admin.getFirstName());
        Assertions.assertEquals("Vagger", admin.getLastName());
        Assertions.assertEquals(30, admin.getAge()); // Example age
        Assertions.assertEquals("ADMIN", admin.getRole());
        Assertions.assertEquals("Male", admin.getGender());
        Assertions.assertEquals("1234567890", admin.getContactNumber());
        Assertions.assertEquals("admin123", admin.getAdminId());
        Assertions.assertEquals("Prasad@123", admin.getPassword());
        Assertions.assertEquals("prasad.vagger@gmail.com", admin.getEmail());
    }
}
