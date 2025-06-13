package com.hcl.patienttracker.controller;

import com.hcl.patienttracker.dto.ClerkDto;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Clerk;
import com.hcl.patienttracker.service.ClerkService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ClerkControllerTest {
	@InjectMocks
	private ClerkController clerkController;
	@Mock
	private ClerkService clerkService;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllClerks() {
		Clerk clerk1 = new Clerk();
		Clerk clerk2 = new Clerk();
		List<Clerk> clerks = Arrays.asList(clerk1, clerk2);
		when(clerkService.getAllClerks()).thenReturn(clerks);
		ResponseEntity<List<Clerk>> response = clerkController.getAllClerks();
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(2, response.getBody().size());
	}

	@Test
	public void testSaveClerk() {
		ClerkDto clerkDto = new ClerkDto();
		Map<String, String> responseMap = Map.of("message", "Clerk saved successfully");
		when(clerkService.registerClerk(any(ClerkDto.class))).thenReturn(responseMap);
		ResponseEntity<Map<String, String>> response = clerkController.saveClerk(clerkDto);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Clerk saved successfully", response.getBody().get("message"));
	}

	@Test
	public void testGetClerkById() {
		Clerk clerk = new Clerk();
		when(clerkService.getClerkById(1L)).thenReturn(clerk);
		ResponseEntity<Clerk> response = clerkController.getClerkById(1L);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(clerk, response.getBody());
	}

	@Test
	public void testUpdateClerk() {
		ClerkDto clerkDto = new ClerkDto();
		Clerk updatedClerk = new Clerk();
		when(clerkService.updateClerk(eq(1L), any(ClerkDto.class))).thenReturn(updatedClerk);
		ResponseEntity<Clerk> response = clerkController.updateClerk(1L, clerkDto);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals(updatedClerk, response.getBody());
	}

	@Test
	public void testDeleteClerk() {
		doNothing().when(clerkService).deleteClerk(1L);
		ResponseEntity<String> response = clerkController.deleteClerk(1L);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Clerk deleted successfully", response.getBody());
	}

	@Test
	public void testAssignPatientToClerk() {
		doNothing().when(clerkService).assignPatientToClerk(1L, 1L);
		ResponseEntity<String> response = clerkController.assignPatientToClerk(1L, 1L);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("patient assigned to clerk successfully", response.getBody());
	}

	@Test
	public void testUpdatePatientDetails() {
		PatientDto patientDto = new PatientDto();
		doNothing().when(clerkService).updatePatientDetails(1L, 1L, patientDto);
		ResponseEntity<String> response = clerkController.updatePatientDetails(1L, patientDto, 1L);
		assertEquals(200, response.getStatusCodeValue());
		assertEquals("Patient details updated successfully", response.getBody());
	}
}