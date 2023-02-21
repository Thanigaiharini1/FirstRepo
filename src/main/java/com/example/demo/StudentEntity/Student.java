package com.example.demo.StudentEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table
public class Student {

	@Id

	private int id;
	
	@NotNull
    @NotBlank
	@Pattern(regexp ="^[A-Z]{1}[a-z]{1,10}$",message = " Name starts with captial letter ")
	private String name;

	@Email(message = "invalid email address ,must have @ symbol")
	private String emailId;
	
	@NotNull
	@Size(min=2,max=4)
	@NotBlank(message = "Department is Mandatory")
	@Pattern(regexp ="^[A-Z]{1,3}$",message = "Department should be in captial and should have  3 letters")
	private String department;
	
	@NotNull
	@Size(min=2,max=30)
	@Pattern(regexp ="^[A-Z]{1}[a-z]{1,10}$",message ="Location starts with captial and should have 10 letters")
    @NotBlank(message = "Location should not be empty")
	private String location;
    
	public Student() {
		
	}
	public Student(int id, String name, String emailId, String department, String location) {
		super();
		this.id = id;
		this.name = name;
		this.emailId = emailId;
		this.department = department;
		this.location = location;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
	
	
}

