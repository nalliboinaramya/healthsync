package com.hcl.patienttracker.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.patienttracker.entity.Billing;

@Repository
public interface BillingRepository extends JpaRepository<Billing, Long> {
}
