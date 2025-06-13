package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.BillingDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.patienttracker.service.impl.BillingServiceImpl;


@RestController
@RequestMapping("/api/bills")
public class BillingController {

    @Autowired
    private BillingServiceImpl billService;

    @PostMapping("/{prescriptionId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BillingDto> generateBill(@PathVariable Integer prescriptionId) {
        return ResponseEntity.ok(billService.generateBill(prescriptionId));
    }

}