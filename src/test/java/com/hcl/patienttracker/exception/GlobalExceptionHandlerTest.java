package com.hcl.patienttracker.exception;

import com.hcl.patienttracker.dto.ApiError;
import com.hcl.patienttracker.dto.ExceptionResponseDto;
import io.jsonwebtoken.JwtException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GlobalExceptionHandlerTest {

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    public void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    public void testHandleAdminNotExistsException() {
        AdminNotFoundException exception = new AdminNotFoundException("Admin not found");
        ResponseEntity<ApiError> response = globalExceptionHandler.handleAdminNotExistsException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Admin not found", response.getBody().getMessage());
    }

    @Test
    public void testHandleAdminAlreadyExistsException() {
        AdminAlreadyExistsException exception = new AdminAlreadyExistsException("Admin already exists");
        ResponseEntity<ApiError> response = globalExceptionHandler.handleAdminAlreadyExistsException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Admin already exists", response.getBody().getMessage());
    }

    @Test
    public void testHandleAuthenticationException() {
        AuthenticationException exception = mock(AuthenticationException.class);
        when(exception.getMessage()).thenReturn("Unauthorized");

        ResponseEntity<ApiError> response = globalExceptionHandler.handleAuthenticationException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Unauthorized", response.getBody().getMessage());
    }

    @Test
    public void testHandleJwtException() {
        JwtException exception = new JwtException("Invalid token");
        ResponseEntity<ApiError> response = globalExceptionHandler.handleJwtException(exception);

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertEquals("Invalid token", response.getBody().getMessage());
    }
    @Test
    public void testHandleClerkNotFoundException() {
        ClerkNotFoundException exception = new ClerkNotFoundException("Clerk not found");
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleClerkNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Clerk not found", response.getBody().get("error"));
    }

    @Test
    public void testHandleGlobalException() {
        Exception exception = new Exception("An unexpected error occurred");
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleGlobalException(exception);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred: An unexpected error occurred", response.getBody().get("error"));
    }

    @Test
    public void testHandlePatientNotFoundException() {
        PatientNotFoundException exception = new PatientNotFoundException("Patient not found");
        ResponseEntity<ExceptionResponseDto> response = globalExceptionHandler.handlePatientNotFoundException(exception);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Patient not found", response.getBody().getMessage());
    }

    @Test
    public void testHandlePatientAlreadyExists() {
        PatientAlreadyexistsException exception = new PatientAlreadyexistsException("Patient already exists");
        ResponseEntity<ExceptionResponseDto> response = globalExceptionHandler.handlePatientAlreadyExists(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Patient already exists", response.getBody().getMessage());
    }

    @Test
    public void testHandleConsultationNotFoundException() {
        ConsultationNotFoundException exception = new ConsultationNotFoundException("Consultation not found");
        ResponseEntity<ExceptionResponseDto> response = globalExceptionHandler.handleConsultationNotFoundException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Consultation not found", response.getBody().getMessage());
    }

    @Test
    public void testHandleMedicineOutOfStockException() {
        MedicineOutOfStockException exception = new MedicineOutOfStockException("Medicine out of stock");
        ResponseEntity<ExceptionResponseDto> response = globalExceptionHandler.handleMedicineOutOfStockException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Medicine out of stock", response.getBody().getMessage());
    }

    @Test
    public void testHandleClerkAlreadyExistsException() {
        ClerkAlreadyExistsException exception = new ClerkAlreadyExistsException("Clerk already exists");
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleClerkAlreadyExistsException(exception);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Clerk already exists", response.getBody().get("error"));
    }

    @Test
    public void testHandleUnauthorizedAccessException() {
        UnauthorizedAccessException exception = new UnauthorizedAccessException("Unauthorized access");
        ResponseEntity<Map<String, String>> response = globalExceptionHandler.handleUnauthorizedAccessException(exception);

        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
        assertEquals("Unauthorized access", response.getBody().get("error"));
    }
}