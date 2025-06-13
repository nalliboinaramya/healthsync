package com.hcl.patienttracker.repository;

import com.hcl.patienttracker.entity.Clerk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClerkRepository extends JpaRepository<Clerk,Long> {
	
	boolean existsByContactNumber(String contactNumber);
}
