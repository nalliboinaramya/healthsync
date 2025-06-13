package com.hcl.patienttracker.repository;



import com.hcl.patienttracker.entity.Medicine;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long> {
	
	Optional<Medicine> findByNameAndManufacturerAndExpiryDate(String name, String manufacturer, String expiryDate);
}
