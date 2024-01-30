package com.hrm.main.payloads;

import java.time.Duration;
import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.AppraisalQuater;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.models.Helper.EnumCollection.ProbationPeriod;

public class BasicInfoDto {

	private EmployeeStatus employeeStatus;
	private ProbationPeriod probationPeriod;
	private LocalDate dateOfJoining;
	private Duration workExperience;

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

}
