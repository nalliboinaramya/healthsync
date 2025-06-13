package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.PatientDto;

import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.ConsultationNotFoundException;
import com.hcl.patienttracker.exception.PatientAlreadyexistsException;
import com.hcl.patienttracker.exception.PatientNotFoundException;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.impl.PatientServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PatientServiceImplTest {

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private PatientServiceImpl patientService;

    private Patient patient;
    private PatientDto patientDto;

    @BeforeEach
    public void setUp() {
        patient = new Patient();
        patient.setId(1L);
        patient.setEmail("jane.doe@example.com");
        patient.setCity("Bengaluru");

        patientDto = new PatientDto();
        patientDto.setId(1);
        patientDto.setName("Jane Doe");
        patientDto.setDob(new Date());
        patientDto.setGender("Female");
        patientDto.setContactNumber("0987654321");
        patientDto.setEmail("jane.doe@example.com");
        patientDto.setCity("Bengaluru");
        
       
    }
    

    @Test
    void testAddNewPatient_Success() throws PatientAlreadyexistsException {
    
        when(patientRepository.findByNameAndDobAndContactNumber(anyString(), any(Date.class), anyString()))
                .thenReturn(null);
        when(modelMapper.map(any(PatientDto.class), eq(Patient.class))).thenReturn(patient);
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(modelMapper.map(any(Patient.class), eq(PatientDto.class))).thenReturn(patientDto);

    
        PatientDto savedPatient = patientService.addNewPatient(patientDto);

      
        assertNotNull(savedPatient);
        assertEquals(patientDto.getName(), savedPatient.getName());
        verify(patientRepository, times(1))
                .findByNameAndDobAndContactNumber(anyString(), any(Date.class), anyString());
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    void testAddNewPatient_ThrowsPatientAlreadyExistsException() {
     
        when(patientRepository.findByNameAndDobAndContactNumber(anyString(), any(Date.class), anyString()))
                .thenReturn(patient);

        assertThrows(PatientAlreadyexistsException.class,
                () -> patientService.addNewPatient(patientDto));
        verify(patientRepository, never()).save(any(Patient.class));
    }


    @Test
    public void testGetPatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(modelMapper.map(any(Patient.class), eq(PatientDto.class))).thenReturn(patientDto);
        

        PatientDto foundPatient = patientService.getPatient(1L);

        assertNotNull(foundPatient);
        assertEquals(patientDto.getName(), foundPatient.getName());
        verify(patientRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetPatient_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.getPatient(1L));
    }

    @Test
    public void testGetAllPatients() {
        List<Patient> patients = Arrays.asList(patient);
        when(patientRepository.findAll()).thenReturn(patients);
        when(modelMapper.map(any(Patient.class), eq(PatientDto.class))).thenReturn(patientDto);

        List<PatientDto> patientList = patientService.getAllPatients();

        assertNotNull(patientList);
        assertEquals(1, patientList.size());
        verify(patientRepository, times(1)).findAll();
    }
    
    @Test
    public void testDeletePatient() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        patientService.deletePatient(1L);

        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).delete(any(Patient.class));
    }

    @Test
    public void testDeletePatient_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PatientNotFoundException.class, () -> patientService.deletePatient(1L));
    }
    
    @Test
    public void testUpdatePatientDto() {
        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);
        when(modelMapper.map(any(Patient.class), eq(PatientDto.class))).thenReturn(patientDto);

        PatientDto updatedPatient = patientService.updatePatientDto(1L, patientDto);

        assertNotNull(updatedPatient);
        assertEquals(patientDto.getName(), updatedPatient.getName());
        verify(patientRepository, times(1)).findById(1L);
        verify(patientRepository, times(1)).save(any(Patient.class));
    }

    @Test
    public void testUpdatePatientDto_NotFound() {
        when(patientRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(PatientNotFoundException.class, () -> patientService.updatePatientDto(1L, patientDto));
    }


    @Test
    void testHasConsulted_ThrowsConsultationNotFoundException() {
        // Arrange
        patient.setDoctors(Collections.emptyList());
        when(patientRepository.findById(anyLong())).thenReturn(Optional.of(patient));

        // Act & Assert
        assertThrows(ConsultationNotFoundException.class,
                () -> patientService.hasConsulted(1L, 1L));
        verify(patientRepository, times(1)).findById(anyLong());
    }
}