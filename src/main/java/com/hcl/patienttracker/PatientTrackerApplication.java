package com.hcl.patienttracker;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.hcl.patienttracker")
public class PatientTrackerApplication {
	

	public static void main(String[] args) {
		SpringApplication.run(PatientTrackerApplication.class, args);
	}

}
