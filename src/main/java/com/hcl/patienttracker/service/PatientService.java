package com.hcl.patienttracker.service;

import java.util.List;


import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;



public interface PatientService {

	public PatientDto addNewPatient(PatientDto patientDto) throws PatientAlreadyexistsException;
	public PatientDto getPatient(Long id);
	public List<PatientDto> getAllPatients();
	public PatientDto  updatePatientDto(Long id,PatientDto patientDto);
	public void deletePatient(Long id);
	public Boolean hasConsulted(Long patientId, Long doctorId);

}
