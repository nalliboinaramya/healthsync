package com.hcl.patienttracker.service.impl;

import com.hcl.patienttracker.dto.RegistrationRequestDto;
import com.hcl.patienttracker.dto.RegistrationResponseDto;
import com.hcl.patienttracker.entity.Admin;
import com.hcl.patienttracker.exception.AdminAlreadyExistsException;
import com.hcl.patienttracker.exception.AdminNotFoundException;
import com.hcl.patienttracker.repository.AdminRepository;
import com.hcl.patienttracker.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AdminServiceImpl implements AdminService, UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return adminRepository.findByEmail(username).orElseThrow(()->new BadCredentialsException("admin not found with this email :-"+username));

    }
    @Override
    public RegistrationResponseDto saveRegistration(RegistrationRequestDto registrationRequestDto) {
        if (adminRepository.findByEmail(registrationRequestDto.getEmail()).isPresent()) {
            throw new AdminNotFoundException("Email already exists please try with different email");
        }

        if (adminRepository.findByContactNumber(registrationRequestDto.getContactNumber()).isPresent()) {
            throw new AdminAlreadyExistsException("Mobile Number already exists try with different Mobile number");
        }
        //here we are converting registration req dto to entity
        //to have our own custom response type we will use DTO and also for security purpose to we dnt want to expose our entity class to our client
        Admin admin=new Admin();
        admin.setFirstName(registrationRequestDto.getFirstName());
        admin.setLastName(registrationRequestDto.getLastName());
        admin.setAge(registrationRequestDto.getAge());
        admin.setGender(registrationRequestDto.getGender());
        admin.setRole(registrationRequestDto.getRole());
        admin.setContactNumber(registrationRequestDto.getContactNumber());
        admin.setAdminId(registrationRequestDto.getAdminId());
        admin.setEmail(registrationRequestDto.getEmail());
        admin.setPassword(passwordEncoder.encode(registrationRequestDto.getPassword()));
        Admin savedRegistration = adminRepository.save(admin);

//converting the entity into registration response dto..
        RegistrationResponseDto responseDto=new RegistrationResponseDto();
        responseDto.setId(savedRegistration.getId());
        responseDto.setFirstName(savedRegistration.getFirstName());
        responseDto.setLastName(savedRegistration.getLastName());
        responseDto.setAge(savedRegistration.getAge());
        responseDto.setRole(savedRegistration.getRole());
        responseDto.setGender(savedRegistration.getGender());
        responseDto.setEmail(savedRegistration.getEmail());
        responseDto.setContactNumber(savedRegistration.getContactNumber());
        responseDto.setAdminId(savedRegistration.getAdminId());
        return responseDto;
    }

    @Override
    public Admin getAdminById(Long id) {
        return adminRepository.findById(id).orElseThrow(()->new AdminNotFoundException("Admin Not Found"));
    }


}
