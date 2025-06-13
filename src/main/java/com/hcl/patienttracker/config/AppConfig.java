package com.hcl.patienttracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.hcl.patienttracker.entity.PrescriptionMedicine;

@Configuration
public class AppConfig {
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public PrescriptionMedicine prescriptionMedicine() {
        return new PrescriptionMedicine();
    }
}
