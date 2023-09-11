package com.hrm.main.models;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "Onboarding_employee_summary")

public class OnboardingEmployeeSummary {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Summary_Id_Sequence")
	@SequenceGenerator(name="Summary_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Summary_Id_Sequence")
	
	private int id;
	private long employeeid;
	private String employeeName;
	private long mobileNumber;
	private String emailId;	
	@Column(name = "date_of_joining")
	@DateTimeFormat(pattern = "dd-mm-yyyy")
	//@JsonFormat(pattern = "yyyy/dd /mm")
	private LocalDate dateOfJoining;
	private String designation;
	private String department;
	private String status;
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public long getEmployeeid() {
		return employeeid;
	}
	public void setEmployeeid(long employeeid) {
		this.employeeid = employeeid;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public long getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(long mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}
	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	
	
}

