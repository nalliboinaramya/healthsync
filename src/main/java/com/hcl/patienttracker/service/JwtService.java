package com.hcl.patienttracker.service;

import com.hcl.patienttracker.entity.Admin;

public interface JwtService {
    String generateToken(Admin admin);
    Long getAdminIdFromToken(String token);
}
