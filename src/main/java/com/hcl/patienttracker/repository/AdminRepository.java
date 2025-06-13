package com.hcl.patienttracker.repository;

import com.hcl.patienttracker.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin,Long>{
    Optional<Admin> findByEmail(String email);

    Optional<Admin> findByContactNumber(String contactNumber);
}
