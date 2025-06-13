package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.LoginRequestDto;
import com.hcl.patienttracker.dto.LoginResponseDto;
import com.hcl.patienttracker.service.AuthService;
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
public class AuthControllerTest {

    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    private LoginRequestDto loginRequestDto;
    LoginResponseDto loginResponseDto;

    @BeforeEach
    public void setUp() {
        loginRequestDto = new LoginRequestDto();
        loginRequestDto.setEmail("admin@example.com");
        loginRequestDto.setPassword("password");

        loginResponseDto = new LoginResponseDto();
        loginResponseDto.setType("Bearer");
        loginResponseDto.setToken("dummy-token");
    }

    @Test
    public void testLogin() {
        when(authService.login(any(LoginRequestDto.class))).thenReturn(loginResponseDto);

        ResponseEntity<LoginResponseDto> response = authController.login(loginRequestDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(loginResponseDto, response.getBody());
        verify(authService, times(1)).login(any(LoginRequestDto.class));
    }
}