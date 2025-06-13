package com.hcl.patienttracker.controller;


import com.hcl.patienttracker.dto.MedicineDto;
import com.hcl.patienttracker.exception.MedicineAlreadyExistsException;
import com.hcl.patienttracker.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    @Autowired
    private MedicineService medicineService;

    @PostMapping
    public ResponseEntity<MedicineDto> createMedicine(@Validated @RequestBody MedicineDto medicineDTO) throws MedicineAlreadyExistsException {    
    MedicineDto createdMedicine = medicineService.createMedicine(medicineDTO); 
    return new ResponseEntity<>(createdMedicine, HttpStatus.CREATED);}

    @GetMapping("/{medicineId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineDto> getMedicineById(@PathVariable Long medicineId) {
        MedicineDto medicineDTO = medicineService.getMedicineById(medicineId);
        return new ResponseEntity<>(medicineDTO, HttpStatus.OK);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<MedicineDto>> getAllMedicines() {
        List<MedicineDto> medicines = medicineService.getAllMedicines();
        return new ResponseEntity<>(medicines, HttpStatus.OK);
    }

    @PutMapping("/{medicineId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<MedicineDto> updateMedicine(@PathVariable Long medicineId, @Validated @RequestBody MedicineDto medicineDTO) {
        MedicineDto updatedMedicine = medicineService.updateMedicine(medicineId, medicineDTO);
        return new ResponseEntity<>(updatedMedicine, HttpStatus.OK);
    }

    @DeleteMapping("/{medicineId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> deleteMedicine(@PathVariable Long medicineId) {
        medicineService.deleteMedicine(medicineId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}