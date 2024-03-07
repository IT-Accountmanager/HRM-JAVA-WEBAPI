package com.hrm.main.payloads;

import java.time.Duration;
import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.AppraisalQuater;
import com.hrm.main.models.Helper.EnumCollection.EmployeeCategory;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.ProbationPeriod;

public class BasicInfoDto {

	private LocalDate dateOfJoining;
	private ProbationPeriod probationPeriod;
	private Duration workExperience;
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

	public Duration getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(Duration workExperience) {
		this.workExperience = workExperience;
	}

	public EmployeeCategory getEmployeeCategory() {
		return employeeCategory;
	}

	public void setEmployeeCategory(EmployeeCategory employeeCategory) {
		this.employeeCategory = employeeCategory;
	}

}
