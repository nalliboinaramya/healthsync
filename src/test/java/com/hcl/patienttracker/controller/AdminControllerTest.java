package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.RegistrationRequestDto;
import com.hcl.patienttracker.dto.RegistrationResponseDto;
import com.hcl.patienttracker.service.AdminService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminControllerTest {

    @Mock
    private AdminService adminService;

    @InjectMocks
    private AdminController adminController;

    private RegistrationRequestDto registrationRequestDto;
    private RegistrationResponseDto registrationResponseDto;

    @BeforeEach
    public void setUp() {
        registrationRequestDto = new RegistrationRequestDto();
        registrationRequestDto.setId(1L);
        registrationRequestDto.setFirstName("John");
        registrationRequestDto.setLastName("Doe");
        registrationRequestDto.setAge(30);
        registrationRequestDto.setGender("Male");
        registrationRequestDto.setRole("ADMIN");
        registrationRequestDto.setEmail("john.doe@example.com");
        registrationRequestDto.setContactNumber("1234567890");
        registrationRequestDto.setAdminId("admin123");
        registrationRequestDto.setPassword("password");

        registrationResponseDto = new RegistrationResponseDto();
        registrationResponseDto.setId(1L);
        registrationResponseDto.setFirstName("John");
        registrationResponseDto.setLastName("Doe");
        registrationResponseDto.setAge(30);
        registrationResponseDto.setGender("Male");
        registrationResponseDto.setRole("Admin");
        registrationResponseDto.setEmail("john.doe@example.com");
        registrationResponseDto.setContactNumber("1234567890");
        registrationResponseDto.setAdminId("admin123");
    }

    @Test
    public void testSaveRegistration() {
        when(adminService.saveRegistration(any(RegistrationRequestDto.class))).thenReturn(registrationResponseDto);

        ResponseEntity<RegistrationResponseDto> response = adminController.saveRegistration(registrationRequestDto);

      //  assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(registrationResponseDto, response.getBody());
        verify(adminService, times(1)).saveRegistration(any(RegistrationRequestDto.class));
    }
}