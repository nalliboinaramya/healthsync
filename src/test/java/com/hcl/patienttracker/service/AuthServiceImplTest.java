package com.hcl.patienttracker.service;
import com.hcl.patienttracker.dto.LoginRequestDto;
import com.hcl.patienttracker.dto.LoginResponseDto;
import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.service.JwtService;
import com.hcl.patienttracker.service.impl.AuthServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AuthServiceImplTest {

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private JwtService jwtService;

    @InjectMocks
    private AuthServiceImpl authService;

    private LoginRequestDto loginRequest;
    private Admin admin;
    private Authentication authentication;

    @BeforeEach
    public void setUp() {
        loginRequest = new LoginRequestDto();
        loginRequest.setEmail("prasad.vagger@gmail.com");
        loginRequest.setPassword("Prasad@123");

        admin = new Admin();
        admin.setEmail("prasad.vagger@gmail.com");
        admin.setPassword("Prasad@123");

        authentication = new UsernamePasswordAuthenticationToken(admin, null, admin.getAuthorities());
    }

    @Test
    public void testLogin() {
        when(authenticationManager.authenticate(any(UsernamePasswordAuthenticationToken.class))).thenReturn(authentication);
        when(jwtService.generateToken(admin)).thenReturn("mocked-jwt-token");

        LoginResponseDto response = authService.login(loginRequest);

        assertNotNull(response);
        assertEquals("JWT", response.getType());
        assertEquals("mocked-jwt-token", response.getToken());

        verify(authenticationManager, times(1)).authenticate(any(UsernamePasswordAuthenticationToken.class));
        verify(jwtService, times(1)).generateToken(admin);
    }
}