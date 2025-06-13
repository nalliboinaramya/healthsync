package com.hcl.patienttracker.dto;

import java.util.Date;
import java.util.List;


import com.hcl.patienttracker.entity.Billing;
import com.hcl.patienttracker.entity.Doctor;
import com.hcl.patienttracker.entity.Prescription;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;


public class PatientDto {

	private Integer id;
	
	@NotBlank(message = "Name is mandatory") 
    private String name;
	
	@Past(message = "Date of birth must be in the past")
    private Date dob;
	
	@NotBlank(message = "Gender is mandatory") 
    private String gender;
	
	@NotBlank(message = "Contact number is mandatory")
    @Pattern(regexp = "^\\d{10}$", message = "Contact number must be 10 digits")
    private String contactNumber;
	
	@Email(message = "Email should be valid")
    private String email;
	
    private String city;  
    
    private List<DoctorDto> doctors;
    

    private List<PrescriptionDto> prescriptions;
    

    private List<BillingDto> bills;


	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getContactNumber() {
		return contactNumber;
	}
	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public List<DoctorDto> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<DoctorDto> doctors) {
		this.doctors = doctors;
	}
	public List<PrescriptionDto> getPrescriptions() {
		return prescriptions;
	}
	public void setPrescriptions(List<PrescriptionDto> prescriptions) {
		this.prescriptions = prescriptions;
	}
	public List<BillingDto> getBills() {
		return bills;
	}
	public void setBills(List<BillingDto> bills) {
		this.bills = bills;
	}
	


	

	
}
