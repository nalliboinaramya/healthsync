package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.MedicineDto;
import com.hcl.patienttracker.service.MedicineService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MedicineControllerTest {

    @Mock
    private MedicineService medicineService;

    @InjectMocks
    private MedicineController medicineController;

    private MedicineDto medicineDto;

    @BeforeEach
    public void setUp() {
        medicineDto = new MedicineDto();
        medicineDto.setId(1L);
        medicineDto.setMedicineId("MED123");
        medicineDto.setName("Paracetamol");
        medicineDto.setManufacturer("Pharma Inc.");
        medicineDto.setPrice(10.0);
    }

    @Test
    public void testCreateMedicine() {
        when(medicineService.createMedicine(any(MedicineDto.class))).thenReturn(medicineDto);

        ResponseEntity<MedicineDto> response = medicineController.createMedicine(medicineDto);

        assertNotNull(response);
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(medicineDto, response.getBody());
        verify(medicineService, times(1)).createMedicine(any(MedicineDto.class));
    }

    @Test
    public void testGetMedicineById() {
        when(medicineService.getMedicineById(1L)).thenReturn(medicineDto);

        ResponseEntity<MedicineDto> response = medicineController.getMedicineById(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicineDto, response.getBody());
        verify(medicineService, times(1)).getMedicineById(1L);
    }

    @Test
    public void testGetAllMedicines() {
        List<MedicineDto> medicineList = Arrays.asList(medicineDto);
        when(medicineService.getAllMedicines()).thenReturn(medicineList);

        ResponseEntity<List<MedicineDto>> response = medicineController.getAllMedicines();

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicineList, response.getBody());
        verify(medicineService, times(1)).getAllMedicines();
    }

    @Test
    public void testUpdateMedicine() {
        when(medicineService.updateMedicine(1L, medicineDto)).thenReturn(medicineDto);

        ResponseEntity<MedicineDto> response = medicineController.updateMedicine(1L, medicineDto);

        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(medicineDto, response.getBody());
        verify(medicineService, times(1)).updateMedicine(1L, medicineDto);
    }

    @Test
    public void testDeleteMedicine() {
        doNothing().when(medicineService).deleteMedicine(1L);

        ResponseEntity<Void> response = medicineController.deleteMedicine(1L);

        assertNotNull(response);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(medicineService, times(1)).deleteMedicine(1L);
    }

//    @Test
//    public void testAssignMedicineToPatient() {
//        Hashtable<String, Object> result = new Hashtable<>();
//        result.put("patient", new Object());
//        result.put("Medicine", Arrays.asList(medicineDto));
//        when(medicineService.assignMedicineToPatient(1L, 1L)).thenReturn(result);
//
//        ResponseEntity<Hashtable<String, Object>> response = medicineController.assignMedicineToPatient(1L, 1L);
//
//        assertNotNull(response);
//        assertEquals(HttpStatus.OK, response.getStatusCode());
//        assertEquals(result, response.getBody());
//        verify(medicineService, times(1)).assignMedicineToPatient(1L, 1L);
//    }
}