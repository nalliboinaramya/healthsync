package com.hcl.patienttracker.service;

import com.hcl.patienttracker.dto.ClerkDto;
import com.hcl.patienttracker.dto.PatientDto;
import com.hcl.patienttracker.entity.Clerk;
import com.hcl.patienttracker.entity.Patient;
import com.hcl.patienttracker.exception.*;
import com.hcl.patienttracker.repository.ClerkRepository;
import com.hcl.patienttracker.repository.PatientRepository;
import com.hcl.patienttracker.service.ClerkService;
import com.hcl.patienttracker.service.impl.ClerkServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

public class ClerkServiceImplTest {
	@InjectMocks
	private ClerkServiceImpl clerkService;
	@Mock
	private ClerkRepository clerkRepository;
	@Mock
	private PatientRepository patientRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllClerks() {
		Clerk clerk1 = new Clerk();
		Clerk clerk2 = new Clerk();
		List<Clerk> clerks = Arrays.asList(clerk1, clerk2);
		when(clerkRepository.findAll()).thenReturn(clerks);
		List<Clerk> result = clerkService.getAllClerks();
		assertEquals(2, result.size());
	}

	@Test
	public void testRegisterClerk() {
		ClerkDto clerkDto = new ClerkDto();
		clerkDto.setContactNumber("1234567890");
		when(clerkRepository.existsByContactNumber(clerkDto.getContactNumber())).thenReturn(false);
		Map<String, String> response = clerkService.registerClerk(clerkDto);
		assertEquals("Your details submitted successfully", response.get("message"));
		verify(clerkRepository, times(1)).save(any(Clerk.class));
	}

	@Test
	public void testRegisterClerkAlreadyExists() {
		ClerkDto clerkDto = new ClerkDto();
		clerkDto.setContactNumber("1234567890");
		when(clerkRepository.existsByContactNumber(clerkDto.getContactNumber())).thenReturn(true);
		assertThrows(ClerkAlreadyExistsException.class, () -> {
			clerkService.registerClerk(clerkDto);
		});
	}

	@Test
	public void testGetClerkById() {
		Clerk clerk = new Clerk();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		Clerk result = clerkService.getClerkById(1L);
		assertEquals(clerk, result);
	}

	@Test
	public void testGetClerkByIdNotFound() {
		when(clerkRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ClerkNotFoundException.class, () -> {
			clerkService.getClerkById(1L);
		});
	}

	@Test
	public void testUpdateClerk() {
		ClerkDto clerkDto = new ClerkDto();
		Clerk clerk = new Clerk();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(clerkRepository.save(any(Clerk.class))).thenReturn(clerk);
		Clerk result = clerkService.updateClerk(1L, clerkDto);
		assertEquals(clerk, result);
	}

	@Test
	public void testUpdateClerkNotFound() {
		ClerkDto clerkDto = new ClerkDto();
		when(clerkRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ClerkNotFoundException.class, () -> {
			clerkService.updateClerk(1L, clerkDto);
		});
	}

	@Test
	public void testDeleteClerk() {
		Clerk clerk = new Clerk();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		clerkService.deleteClerk(1L);
		verify(clerkRepository, times(1)).delete(clerk);
	}

	@Test
	public void testDeleteClerkNotFound() {
		when(clerkRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ClerkNotFoundException.class, () -> {
			clerkService.deleteClerk(1L);
		});
	}

	@Test
	public void testAssignPatientToClerk() {
		Clerk clerk = new Clerk();
		Patient patient = new Patient();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
		clerkService.assignPatientToClerk(1L, 1L);
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	public void testAssignPatientToClerkClerkNotFound() {
		when(clerkRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(ClerkNotFoundException.class, () -> {
			clerkService.assignPatientToClerk(1L, 1L);
		});
	}

	@Test
	public void testAssignPatientToClerkPatientNotFound() {
		Clerk clerk = new Clerk();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(patientRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class, () -> {
			clerkService.assignPatientToClerk(1L, 1L);
		});
	}

	@Test
	public void testUpdatePatientDetails() {
		Clerk clerk = new Clerk();
		clerk.setId(1L);
		Patient patient = new Patient();
		patient.setClerk(clerk);
		PatientDto patientDto = new PatientDto();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
		clerkService.updatePatientDetails(1L, 1L, patientDto);
		verify(patientRepository, times(1)).save(patient);
	}

	@Test
	public void testUpdatePatientDetailsUnauthorized() {
		Clerk clerk = new Clerk();
		clerk.setId(1L);
		Clerk anotherClerk = new Clerk();
		anotherClerk.setId(2L);
		Patient patient = new Patient();
		patient.setClerk(anotherClerk);
		PatientDto patientDto = new PatientDto();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
		assertThrows(UnauthorizedAccessException.class, () -> {
			clerkService.updatePatientDetails(1L, 1L, patientDto);
		});
	}

	@Test
	public void testUpdatePatientDetailsPatientNotFound() {
		Clerk clerk = new Clerk();
		PatientDto patientDto = new PatientDto();
		when(clerkRepository.findById(1L)).thenReturn(Optional.of(clerk));
		when(patientRepository.findById(1L)).thenReturn(Optional.empty());
		assertThrows(PatientNotFoundException.class, () -> {
			clerkService.updatePatientDetails(1L, 1L, patientDto);
		});
	}
}