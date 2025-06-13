package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.BillingDto;

public interface BillingService {
    BillingDto generateBill(Integer prescriptionId);
}
