package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.RegistrationRequestDto;
import com.hcl.patienttracker.dto.RegistrationResponseDto;
import com.hcl.patienttracker.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    Logger log = LoggerFactory.getLogger(AdminController.class);
    @Autowired
    private AdminService adminService;
    @PostMapping("/saveRegistration")
    public ResponseEntity<RegistrationResponseDto> saveRegistration(@RequestBody RegistrationRequestDto registrationRequestDto)
    {
        log.info("sajfsaljfalsjlaf");
        RegistrationResponseDto responseDto = adminService.saveRegistration(registrationRequestDto);
        log.info("diofuiosurf");
        return  new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
