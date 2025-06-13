package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.LoginRequestDto;
import com.hcl.patienttracker.dto.LoginResponseDto;

public interface AuthService {
    LoginResponseDto login(LoginRequestDto loginRequestDto);
}
