package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.RegistrationRequestDto;
import com.hcl.patienttracker.dto.RegistrationResponseDto;
import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.exception.AdminAlreadyExistsException;
import com.hcl.patienttracker.exception.AdminNotFoundException;
import com.hcl.patienttracker.repository.AdminRepository;
import com.hcl.patienttracker.service.impl.AdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AdminServiceImplTest {

    @Mock
    private AdminRepository adminRepository;

    @InjectMocks
    private AdminServiceImpl adminService;

    @Mock
    private PasswordEncoder passwordEncoder;

    private RegistrationRequestDto registrationDto;
    private Admin admin;

    @BeforeEach
    public void setUp() {
        registrationDto = new RegistrationRequestDto();
        registrationDto.setFirstName("Prasad");
        registrationDto.setLastName("Vagger");
        registrationDto.setAge(30);
        registrationDto.setRole("ADMIN");
        registrationDto.setGender("Male");
        registrationDto.setContactNumber("1234567890");
        registrationDto.setAdminId("admin123");
        registrationDto.setPassword("Prasad@123");
        registrationDto.setEmail("prasad.vagger@gmail.com");

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
    public void testCreateAdmin() {
        when(adminRepository.save(any(Admin.class))).thenReturn(admin);

        RegistrationResponseDto createdAdmin = adminService.saveRegistration(registrationDto);

        assertNotNull(createdAdmin);
        assertEquals("Prasad", createdAdmin.getFirstName());
        assertEquals("Vagger", createdAdmin.getLastName());
        assertEquals(30, createdAdmin.getAge());
        assertEquals("ADMIN", createdAdmin.getRole());
        assertEquals("Male", createdAdmin.getGender());
        assertEquals("1234567890", createdAdmin.getContactNumber());
        assertEquals("admin123", createdAdmin.getAdminId());
        assertEquals("prasad.vagger@gmail.com", createdAdmin.getEmail());

        verify(adminRepository, times(1)).save(any(Admin.class));
    }

    @Test
    public void testGetAdminById() {
        when(adminRepository.findById(1L)).thenReturn(Optional.of(admin));

        Admin foundAdmin = adminService.getAdminById(1L);

        assertNotNull(foundAdmin);
        assertEquals("Prasad", foundAdmin.getFirstName());
        assertEquals("Vagger", foundAdmin.getLastName());
        assertEquals(30, foundAdmin.getAge());
        assertEquals("ADMIN", foundAdmin.getRole());
        assertEquals("Male", foundAdmin.getGender());
        assertEquals("1234567890", foundAdmin.getContactNumber());
        assertEquals("admin123", foundAdmin.getAdminId());
        assertEquals("Prasad@123", foundAdmin.getPassword());
        assertEquals("prasad.vagger@gmail.com", foundAdmin.getEmail());

        verify(adminRepository, times(1)).findById(1L);
    }

    @Test
    public void testLoadUserByUsername() {
        when(adminRepository.findByEmail("prasad.vagger@gmail.com")).thenReturn(Optional.of(admin));

        UserDetails userDetails = adminService.loadUserByUsername("prasad.vagger@gmail.com");

        assertNotNull(userDetails);
//        assertEquals("prasad.vagger@gmail.com", userDetails.getUsername());
        assertEquals(admin.getPassword(), userDetails.getPassword()); // Check password if applicable
        assertTrue(userDetails.isAccountNonExpired()); // Additional checks if needed
        assertTrue(userDetails.isAccountNonLocked());
        assertTrue(userDetails.isCredentialsNonExpired());
        assertTrue(userDetails.isEnabled());

        verify(adminRepository, times(1)).findByEmail("prasad.vagger@gmail.com");
    }

    @Test
    public void testLoadUserByUsername_NotFound() {
        when(adminRepository.findByEmail("unknown@gmail.com")).thenReturn(Optional.empty());

        assertThrows(BadCredentialsException.class, () -> {
            adminService.loadUserByUsername("unknown@gmail.com");
        });

        verify(adminRepository, times(1)).findByEmail("unknown@gmail.com");
    }

    @Test
    public void testSaveRegistration_EmailAlreadyExists() {
        when(adminRepository.findByEmail("prasad.vagger@gmail.com")).thenReturn(Optional.of(admin));

        assertThrows(AdminNotFoundException.class, () -> {
            adminService.saveRegistration(registrationDto);
        });

        verify(adminRepository, times(1)).findByEmail("prasad.vagger@gmail.com");
    }

    @Test
    public void testSaveRegistration_ContactNumberAlreadyExists() {
        when(adminRepository.findByContactNumber("1234567890")).thenReturn(Optional.of(admin));

        assertThrows(AdminAlreadyExistsException.class, () -> {
            adminService.saveRegistration(registrationDto);
        });

        verify(adminRepository, times(1)).findByContactNumber("1234567890");
    }
}