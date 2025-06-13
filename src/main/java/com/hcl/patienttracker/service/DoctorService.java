package com.hcl.patienttracker.service;


import com.hcl.patienttracker.dto.DoctorDto;

import java.util.List;

public interface DoctorService {
    DoctorDto createDoctor(DoctorDto doctorDTO);
    DoctorDto getDoctorById(Long doctorId);
    List<DoctorDto> getAllDoctors();
    DoctorDto updateDoctor(Long doctorId, DoctorDto doctorDTO);
    void deleteDoctor(Long doctorId);
    DoctorDto assignpatient(Long doctorId, Long patientId);
}
