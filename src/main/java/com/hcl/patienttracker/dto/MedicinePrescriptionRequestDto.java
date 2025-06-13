package com.hcl.patienttracker.dto;

import java.util.Map;

public class MedicinePrescriptionRequestDto {
	
	Long patientId;
	Long doctorId;
	Map<Long,Integer>  medicineDoseMap;
	public Long getPatientId() {
		return patientId;
	}
	public void setPatientId(Long patientId) {
		this.patientId = patientId;
	}
	public Long getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(Long doctorId) {
		this.doctorId = doctorId;
	}
	public Map<Long, Integer> getMedicineDoseMap() {
		return medicineDoseMap;
	}
	public void setMedicineDoseMap(Map<Long, Integer> medicineDoseMap) {
		this.medicineDoseMap = medicineDoseMap;
	}
	
	

}
