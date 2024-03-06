package com.hrm.payloads;

import java.time.Duration;
import java.time.LocalDate;
import java.time.Period;

import com.hrm.Helper.EnumCollection.AppraisalQuater;
import com.hrm.Helper.EnumCollection.EmployeeCategory;
import com.hrm.Helper.EnumCollection.EmployeeStatus;
import com.hrm.Helper.EnumCollection.ProbationPeriod;

public class BasicInfoDto {

	private LocalDate dateOfJoining;
	private ProbationPeriod probationPeriod;
	private String workExperience;
	private EmployeeStatus employeeStatus;
	private EmployeeCategory employeeCategory;

	public BasicInfoDto() {
		super();
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public ProbationPeriod getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(ProbationPeriod probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

}
