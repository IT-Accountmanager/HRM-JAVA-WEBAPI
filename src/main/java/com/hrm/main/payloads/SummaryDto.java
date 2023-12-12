package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;

public class SummaryDto {
	private String employeeId;// E
	private String name;// E
	private long contactNumber;// O
	private String emailId;// O
	private LocalDate dateOfJoining;// O
	private long bondBreakAmount;// O
	private float relevantExperience = 0.0f;
	private String designation;// E
	private Departments department;
	private String assignTo;
	private EmployeeStatus employeeStatus;// O

	public SummaryDto() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public long getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(long bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public float getRelevantExperience() {
		return relevantExperience;
	}

	public void setRelevantExperience(float relevantExperience) {
		this.relevantExperience = relevantExperience;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

}
