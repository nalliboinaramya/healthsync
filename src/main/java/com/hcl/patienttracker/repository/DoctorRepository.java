package com.hcl.patienttracker.repository;


import com.hcl.patienttracker.entity.Doctor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {
	
	Optional<Doctor> findByFirstNameAndLastNameAndContactNumber(String firstName, String lastName, String contactNumber);

}
