package com.hcl.patienttracker.service;


import com.hcl.patienttracker.dto.MedicineDto;

import java.util.List;

public interface MedicineService {
    MedicineDto createMedicine(MedicineDto medicineDTO);
    MedicineDto getMedicineById(Long medicineId);
    List<MedicineDto> getAllMedicines();
    MedicineDto updateMedicine(Long medicineId, MedicineDto medicineDTO);
    void deleteMedicine(Long medicineId);

}
