package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.MedicineDto;
import com.hcl.patienttracker.entity.Medicine;
import com.hcl.patienttracker.exception.MedicineAlreadyExistsException;
import com.hcl.patienttracker.exception.MedicineNotFoundException;
import com.hcl.patienttracker.repository.MedicineRepository;
import com.hcl.patienttracker.service.impl.MedicineServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MedicineServiceImplTest {

    @Mock
    private MedicineRepository medicineRepository;

    @Mock
    private ModelMapper modelMapper;

    @InjectMocks
    private MedicineServiceImpl medicineService;

    private MedicineDto medicineDto;
    private Medicine medicine;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        medicineDto = new MedicineDto();
        medicineDto.setId(1L);
        medicineDto.setMedicineId(String.valueOf(1L));
        medicineDto.setName("Paracetamol");
        medicineDto.setManufacturer("ABC Pharma");
        medicineDto.setPrice(10.0);
        medicineDto.setStock(100);
        medicineDto.setExpiryDate("2025-12-31");

        medicine = new Medicine();
        medicine.setId(1L);
        medicine.setMedicineId(String.valueOf(1L));
        medicine.setName("Paracetamol");
        medicine.setManufacturer("ABC Pharma");
        medicine.setPrice(10.0);
        medicine.setStock(100);
        medicine.setExpiryDate("2025-12-31");
    }

    @Test
    public void testCreateMedicine() throws MedicineAlreadyExistsException {
        when(medicineRepository.findByNameAndManufacturerAndExpiryDate(anyString(), anyString(), anyString())).thenReturn(Optional.empty());
        when(modelMapper.map(medicineDto, Medicine.class)).thenReturn(medicine);
        when(medicineRepository.save(medicine)).thenReturn(medicine);
        when(modelMapper.map(medicine, MedicineDto.class)).thenReturn(medicineDto);

        MedicineDto createdMedicine = medicineService.createMedicine(medicineDto);

        assertNotNull(createdMedicine);
        assertEquals(medicineDto.getName(), createdMedicine.getName());
        verify(medicineRepository, times(1)).save(medicine);
    }

    @Test
    public void testGetMedicineById() {
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.of(medicine));
        when(modelMapper.map(medicine, MedicineDto.class)).thenReturn(medicineDto);

        MedicineDto foundMedicine = medicineService.getMedicineById(1L);

        assertNotNull(foundMedicine);
        assertEquals(medicineDto.getName(), foundMedicine.getName());
    }

    @Test
    public void testGetMedicineById_NotFound() {
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(MedicineNotFoundException.class, () -> {
            medicineService.getMedicineById(1L);
        });
    }

    @Test
    public void testUpdateMedicine() {
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.of(medicine));
        when(medicineRepository.save(medicine)).thenReturn(medicine);
        when(modelMapper.map(medicine, MedicineDto.class)).thenReturn(medicineDto);

        MedicineDto updatedMedicine = medicineService.updateMedicine(1L, medicineDto);

        assertNotNull(updatedMedicine);
        assertEquals(medicineDto.getName(), updatedMedicine.getName());
        verify(medicineRepository, times(1)).save(medicine);
    }

    @Test
    public void testDeleteMedicine() {
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.of(medicine));

        medicineService.deleteMedicine(1L);

        verify(medicineRepository, times(1)).delete(medicine);
    }

    @Test
    public void testDeleteMedicine_NotFound() {
        when(medicineRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(MedicineNotFoundException.class, () -> {
            medicineService.deleteMedicine(1L);
        });
    }
}