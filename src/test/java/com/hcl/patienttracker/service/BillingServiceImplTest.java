package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.BillingDto;
import com.hcl.patienttracker.entity.*;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.repository.*;
import com.hcl.patienttracker.service.impl.BillingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BillingServiceImplTest {

	@Mock
    private PrescriptionRepository prescriptionRepository;
 
    @Mock
    private MedicineRepository medicineRepository;
 
    @Mock
    private BillingRepository billingRepository;
 
    @Mock
    private PatientRepository patientRepository;
 
    @Mock
    private ModelMapper modelMapper;
 
    @InjectMocks
    private BillingServiceImpl billingService;
 
    @Test
    void testGenerateBill_NewBill() {
        // Arrange
        Integer prescriptionId = 1;
 
        Prescription prescription = new Prescription();
        prescription.setPrescriptionId(prescriptionId);
        prescription.setBilling(null);
 
        Patient patient = new Patient();
        prescription.setPatient(patient);
 
        Medicine medicine = new Medicine();
        medicine.setStock(100);
 
        PrescriptionMedicine pm = new PrescriptionMedicine();
        pm.setMedicine(medicine);
        pm.setPrescribedQuantity(2);
        pm.setPrice(50.0);
 
        prescription.setPrescriptionMedicines(List.of(pm));
 
        Billing billing = new Billing();
        Billing savedBilling = new Billing();
        savedBilling.setTotalCost(100.0);
 
        BillingDto billingDto = new BillingDto();
        billingDto.setTotalCost(100.0);
 
        // Mocking dependencies
        when(prescriptionRepository.findById(prescriptionId)).thenReturn(Optional.of(prescription));
        when(medicineRepository.save(any(Medicine.class))).thenReturn(medicine);
        when(billingRepository.save(any(Billing.class))).thenReturn(savedBilling);
        when(modelMapper.map(savedBilling, BillingDto.class)).thenReturn(billingDto);
 
        // Act
        BillingDto result = billingService.generateBill(prescriptionId);
 
        // Assert
        assertNotNull(result);
        assertEquals(100.0, result.getTotalCost());
        verify(prescriptionRepository, times(1)).findById(prescriptionId);
        verify(medicineRepository, times(1)).save(any(Medicine.class));
        verify(billingRepository, times(1)).save(any(Billing.class));
        verify(patientRepository, times(1)).save(any(Patient.class));
    }
 
    @Test
    void testGenerateBill_ExistingBill() {
        // Arrange
        Integer prescriptionId = 1;
        Prescription prescription = new Prescription();
        Billing existingBilling = new Billing();
        existingBilling.setTotalCost(150.0);
 
        prescription.setBilling(existingBilling);
 
        BillingDto billingDto = new BillingDto();
        billingDto.setTotalCost(150.0);
 
        when(prescriptionRepository.findById(prescriptionId)).thenReturn(Optional.of(prescription));
        when(modelMapper.map(existingBilling, BillingDto.class)).thenReturn(billingDto);
 
        // Act
        BillingDto result = billingService.generateBill(prescriptionId);
 
        // Assert
        assertNotNull(result);
        assertEquals(150.0, result.getTotalCost());
        verify(billingRepository, never()).save(any(Billing.class));
    }
 
    @Test
    void testGenerateBill_PrescriptionNotFound() {
        // Arrange
        Integer prescriptionId = 1;
        when(prescriptionRepository.findById(prescriptionId)).thenReturn(Optional.empty());
 
        // Act & Assert
        assertThrows(ResourceNotFoundException.class, () -> billingService.generateBill(prescriptionId));
        verify(billingRepository, never()).save(any(Billing.class));
    }

}
