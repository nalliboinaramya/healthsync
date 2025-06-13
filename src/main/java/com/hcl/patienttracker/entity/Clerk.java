package com.hcl.patienttracker.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;




@Entity(name="CLERK")
public class Clerk {
	   
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "clerkId")
	    private Long id;

	    
	    
	    @Column(name = "firstName",nullable=false)
	    private String firstName;
	   
	    
	    @Column(name = "lastName",nullable=false)
	    private String lastName;

	    
	    @Column(name = "age",nullable=false)
	    private int age;

	   
	    @Column(name = "contactNumber",nullable=false,unique=true)
	    private String contactNumber;
	    
	   
	    @Column(name = "gender",nullable=false)
	    private String gender;


		public Long getId() {
			return id;
		}


		public void setId(Long id) {
			this.id = id;
		}


		public String getFirstName() {
			return firstName;
		}


		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}


		public String getLastName() {
			return lastName;
		}


		public void setLastName(String lastName) {
			this.lastName = lastName;
		}


		public int getAge() {
			return age;
		}


		public void setAge(int age) {
			this.age = age;
		}


		public String getContactNumber() {
			return contactNumber;
		}


		public void setContactNumber(String contactNumber) {
			this.contactNumber = contactNumber;
		}


		public String getGender() {
			return gender;
		}


		public void setGender(String gender) {
			this.gender = gender;
		}
	      
	  
	   
}
