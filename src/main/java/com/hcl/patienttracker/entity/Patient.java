package com.hcl.patienttracker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;




@Entity
@Table(name =  "patient")
public class Patient {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
    private Long id;
		
	@Column(name = "name",nullable = false)
    private String name;
	
	
	@Column(name = "dob",nullable = false)
    private Date dob;
	
 
	@Column(name = "gender",nullable = false)
    private String gender;
	
	@Column(name = "contact_no",nullable = false)
    private String contactNumber;
	
	
	@Column(name = "email")
    private String email;
	
	@Column(name = "city")
    private String city;
    
	@ManyToMany(mappedBy = "patients")
	@JsonManagedReference(value = "patients-doctors")
    private List<Doctor> doctors = new ArrayList<>();

    @OneToMany(mappedBy = "patient", cascade = CascadeType.ALL	)
    @JsonManagedReference(value = "patient-prescriptions")
    private List<Prescription> prescriptions =  new ArrayList<>();


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "patient_id")
    private List<Billing> bills = new ArrayList<>();
    
    @ManyToOne
    @JoinColumn(name = "clerk_id")
    @JsonIgnore
    private Clerk clerk;

	

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public List<Doctor> getDoctors() {
		return doctors;
	}
	public void setDoctors(List<Doctor> doctors) {
		this.doctors = doctors;
	}

	public List<Prescription> getPrescriptions() {
		return prescriptions;
	}

	public void setPrescriptions(List<Prescription> prescriptions) {
		this.prescriptions = prescriptions;
	}

	public List<Billing> getBills() {
		return bills;
	}

	public void setBills(List<Billing> bills) {
		this.bills = bills;
	}

	public Patient() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Clerk getClerk() {
		return clerk;
	}

	public void setClerk(Clerk clerk) {
		this.clerk = clerk;
	}
	
	

}
