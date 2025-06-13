package com.hcl.patienttracker.service;

import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.dto.RegistrationRequestDto;
import com.hcl.patienttracker.dto.RegistrationResponseDto;

public interface AdminService {
    RegistrationResponseDto saveRegistration(RegistrationRequestDto registrationRequestDto);
    Admin getAdminById(Long id);
}
