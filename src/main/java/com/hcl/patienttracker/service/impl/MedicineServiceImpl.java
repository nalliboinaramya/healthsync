package com.hcl.patienttracker.service.impl;


import com.hcl.patienttracker.exception.MedicineAlreadyExistsException;
import com.hcl.patienttracker.exception.MedicineNotFoundException;
import com.hcl.patienttracker.dto.MedicineDto;
import com.hcl.patienttracker.entity.Medicine;
import com.hcl.patienttracker.repository.MedicineRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.MedicineService;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicineServiceImpl implements MedicineService {

	@Autowired
    private MedicineRepository medicineRepository;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public MedicineDto createMedicine(MedicineDto medicineDTO) throws MedicineAlreadyExistsException {
        Medicine savedMedicine = null;
        if (medicineRepository.findByNameAndManufacturerAndExpiryDate(
                medicineDTO.getName(), medicineDTO.getManufacturer(), medicineDTO.getExpiryDate()).isEmpty()) {
            Medicine medicine = modelMapper.map(medicineDTO, Medicine.class);
            savedMedicine = medicineRepository.save(medicine);
        } else {
            throw new MedicineAlreadyExistsException("Medicine with given name, manufacturer, or expiry date already exists.");
        }
        return modelMapper.map(savedMedicine, MedicineDto.class);
    }

    @Override
    public MedicineDto getMedicineById(Long medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id: " + medicineId));
        return convertToDTO(medicine);
    }

    @Override
    public List<MedicineDto> getAllMedicines() {
        List<Medicine> medicines = medicineRepository.findAll();
        return medicines.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public MedicineDto updateMedicine(Long medicineId, MedicineDto medicineDTO) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id: " + medicineId));
        medicine.setMedicineId(medicineDTO.getMedicineId());
        medicine.setName(medicineDTO.getName());
        medicine.setManufacturer(medicineDTO.getManufacturer());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setExpiryDate(medicineDTO.getExpiryDate());
        Medicine updatedMedicine = medicineRepository.save(medicine);
        return convertToDTO(updatedMedicine);
    }

    @Override
    public void deleteMedicine(Long medicineId) {
        Medicine medicine = medicineRepository.findById(medicineId)
                .orElseThrow(() -> new MedicineNotFoundException("Medicine not found with id: " + medicineId));
        medicineRepository.delete(medicine);
    }

    private MedicineDto convertToDTO(Medicine medicine) {
        MedicineDto medicineDTO = new MedicineDto();
        medicineDTO.setId(medicine.getId());
        medicineDTO.setMedicineId(medicine.getMedicineId());
        medicineDTO.setName(medicine.getName());
        medicineDTO.setManufacturer(medicine.getManufacturer());
        medicineDTO.setPrice(medicine.getPrice());
        medicineDTO.setStock(medicine.getStock());
        medicineDTO.setExpiryDate(medicine.getExpiryDate());
        return medicineDTO;
    }

    private Medicine convertToEntity(MedicineDto medicineDTO) {
        Medicine medicine = new Medicine();
        medicine.setId(medicineDTO.getId());
        medicine.setMedicineId(medicineDTO.getMedicineId());
        medicine.setName(medicineDTO.getName());
        medicine.setManufacturer(medicineDTO.getManufacturer());
        medicine.setPrice(medicineDTO.getPrice());
        medicine.setStock(medicineDTO.getStock());
        medicine.setExpiryDate(medicineDTO.getExpiryDate());
        return medicine;
    }
}
