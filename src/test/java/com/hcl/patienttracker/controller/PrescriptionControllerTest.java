package com.hcl.patienttracker.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hcl.patienttracker.dto.MedicinePrescriptionRequestDto;
import com.hcl.patienttracker.entity.Prescription;
import com.hcl.patienttracker.service.PrescriptionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
 
import java.util.Collections;
import java.util.Optional;
 
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
 
@ExtendWith(MockitoExtension.class)
public class PrescriptionControllerTest {

	private MockMvc mockMvc;
	 
    @Mock
    private PrescriptionService prescriptionService;
 
    @InjectMocks
    private PrescriptionController prescriptionController;
 
    private Prescription prescription;
 
    @Autowired
    private ObjectMapper objectMapper;
 
 
    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(prescriptionController).build();
        objectMapper = new ObjectMapper();
 
        prescription = new Prescription();
        prescription.setPrescriptionId(1);
    }
 
    @Test
    void testGetAllPrescriptions() throws Exception {
        when(prescriptionService.getAllPrescriptions()).thenReturn(Collections.singletonList(prescription));
 
        mockMvc.perform(get("/api/prescription/prescriptions"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].prescriptionId").value(1));
    }
 
    @Test
    void testGetPrescriptionById_Success() throws Exception {
        when(prescriptionService.getPrescriptionById(1)).thenReturn(Optional.of(prescription));
 
        mockMvc.perform(get("/api/prescription/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.prescriptionId").value(1));
    }
 
    @Test
    void testGetPrescriptionById_NotFound() throws Exception {
        when(prescriptionService.getPrescriptionById(1)).thenReturn(Optional.empty());
 
        mockMvc.perform(get("/api/prescription/1"))
                .andExpect(status().isNotFound());
    }
 
    @Test
    void testDeletePrescription_Success() throws Exception {
        doNothing().when(prescriptionService).deletePrescription(1);
 
        mockMvc.perform(delete("/api/prescription/delete/1"))
                .andExpect(status().isNoContent());
    }
 
    @Test
    void testDeletePrescription_NotFound() throws Exception {
        Mockito.doThrow(new RuntimeException("Prescription not found"))
                .when(prescriptionService).deletePrescription(1);
 
        mockMvc.perform(delete("/api/prescription/delete/1"))
                .andExpect(status().isNotFound());
    }
 
    @Test
    void testAddMedicinePrescription_Success() throws Exception {
        MedicinePrescriptionRequestDto dto = new MedicinePrescriptionRequestDto();
        when(prescriptionService.createMedicinePrescription(any(MedicinePrescriptionRequestDto.class)))
                .thenReturn(prescription);
 
        mockMvc.perform(post("/api/prescription/addMedicinePrescrition")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.prescriptionId").value(1));
    }
}
