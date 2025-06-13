package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.LoginRequestDto;
import com.hcl.patienttracker.dto.LoginResponseDto;
import com.hcl.patienttracker.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        LoginResponseDto login = authService.login(loginRequestDto);
        return new ResponseEntity<>(login, HttpStatus.OK);
    }
}
