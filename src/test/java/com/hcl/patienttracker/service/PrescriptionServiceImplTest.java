package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.MedicinePrescriptionRequestDto;
import com.hcl.patienttracker.dto.PrescriptionDto;
import com.hcl.patienttracker.entity.*;
import com.hcl.patienttracker.exception.ConsultationNotFoundException;
import com.hcl.patienttracker.exception.MedicineOutOfStockException;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.exception.PrescriptionNotFoundException;
import com.hcl.patienttracker.exception.ResourceNotFoundException;
import com.hcl.patienttracker.repository.*;
import com.hcl.patienttracker.service.impl.PrescriptionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PrescriptionServiceImplTest {
 
    @Mock
    private PrescriptionRepository prescriptionRepository;
 
    @Mock
    private DoctorRepository doctorRepository;
 
    @Mock
    private PatientRepository patientRepository;
 
    @Mock
    private MedicineRepository medicineRepository;
 
    @Mock
    private MedicineService medicineService;
 
    @Mock
    private PatientService patientService;
 
    @InjectMocks
    private PrescriptionServiceImpl prescriptionService;
 
    private Prescription prescription;
    private Doctor doctor;
    private Patient patient;
    private Medicine medicine;
    private PrescriptionMedicine prescriptionMedicine;
    private MedicinePrescriptionRequestDto medicinePrescriptionDto;
 
    @BeforeEach
    public void setUp() {
        doctor = new Doctor();
        doctor.setDoctorId(1L);
        patient = new Patient();
        patient.setId(1L);
        medicine = new Medicine();
        medicine.setId(1L);
        medicine.setStock(100);
 
        prescriptionMedicine = new PrescriptionMedicine();
        prescriptionMedicine.setMedicine(medicine);
        prescriptionMedicine.setDosage(2);
        prescriptionMedicine.setPrice(10.0);
        prescriptionMedicine.setPrescribedQuantity(2);
 
        prescription = new Prescription();
        prescription.setPrescriptionId(1);
        prescription.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        prescription.setDoctor(doctor);
        prescription.setPatient(patient);
        prescription.setPrescriptionMedicines(Collections.singletonList(prescriptionMedicine));
 
        medicinePrescriptionDto = new MedicinePrescriptionRequestDto();
        medicinePrescriptionDto.setDoctorId(1L);
        medicinePrescriptionDto.setPatientId(1L);
        Map<Long, Integer> medicineDoseMap = new HashMap<>();
        medicineDoseMap.put(1L, 2);
        medicinePrescriptionDto.setMedicineDoseMap(medicineDoseMap);
    }
 
    @Test
    public void testCreateMedicinePrescription_Success() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine));
        when(patientService.hasConsulted(1L, 1L)).thenReturn(true);
        when(prescriptionRepository.save(any(Prescription.class))).thenReturn(prescription);
 
        Prescription savedPrescription = prescriptionService.createMedicinePrescription(medicinePrescriptionDto);
 
        assertNotNull(savedPrescription);
        assertEquals(1, savedPrescription.getPrescriptionId());
        verify(doctorRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).findById(1L);
        verify(medicineRepository, times(1)).findById(1L);
        verify(patientService, times(1)).hasConsulted(1L, 1L);
        verify(prescriptionRepository, times(1)).save(any(Prescription.class));
    }
 
    @Test
    public void testCreateMedicinePrescription_DoctorNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                prescriptionService.createMedicinePrescription(medicinePrescriptionDto));
 
        assertEquals("Doctor not found with given id 1", exception.getMessage());
    }
 
    @Test
    public void testCreateMedicinePrescription_PatientNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
 
        Exception exception = assertThrows(ResourceNotFoundException.class, () ->
                prescriptionService.createMedicinePrescription(medicinePrescriptionDto));
 
        assertEquals("Doctor not found with given id 1", exception.getMessage());
    }
 
    @Test
    public void testCreateMedicinePrescription_ConsultationNotFound() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientService.hasConsulted(1L, 1L)).thenReturn(false);
 
        Exception exception = assertThrows(ConsultationNotFoundException.class, () ->
                prescriptionService.createMedicinePrescription(medicinePrescriptionDto));
 
        assertEquals("Patient did not consulted the given doctor", exception.getMessage());
    }
 
    @Test
    public void testCreateMedicinePrescription_MedicineOutOfStock() {
        when(doctorRepository.findById(1L)).thenReturn(Optional.of(doctor));
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientService.hasConsulted(1L, 1L)).thenReturn(true);
        when(medicineRepository.findById(1L)).thenReturn(Optional.of(medicine));
 
        medicine.setStock(1); // Insufficient stock
 
        Exception exception = assertThrows(MedicineOutOfStockException.class, () ->
                prescriptionService.createMedicinePrescription(medicinePrescriptionDto));
 
        assertEquals("prescribed quantity is not available at the moment", exception.getMessage());
    }
 

 
 
}