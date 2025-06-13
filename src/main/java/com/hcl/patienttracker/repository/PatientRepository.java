package com.hcl.patienttracker.repository;

import java.util.Date;

import com.hcl.patienttracker.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PatientRepository extends JpaRepository<Patient, Long>{

	public Patient findByNameAndDobAndContactNumber(String name, Date dob, String contactNumber);
}
