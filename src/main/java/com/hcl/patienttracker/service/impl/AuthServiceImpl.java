package com.hcl.patienttracker.service.impl;

import com.hcl.patienttracker.dto.LoginRequestDto;
import com.hcl.patienttracker.dto.LoginResponseDto;
import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.service.AuthService;
import com.hcl.patienttracker.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;//it is an interface

    @Autowired
    private JwtService jwtService;

    @Override
    public LoginResponseDto login(LoginRequestDto loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        //if the both credential matches authenticate obj returned
        Admin admin = (Admin) authenticate.getPrincipal();
        //get principle will give you the method if the complete details of the admin , if the details matches it is an inbuilt method
        String token = jwtService.generateToken(admin);
        //if person verified the jwt service will provide the token
        LoginResponseDto response = new LoginResponseDto();
        response.setType("JWT");
        response.setToken(token);

        return response;
    }
}
