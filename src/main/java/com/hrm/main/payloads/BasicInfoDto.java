package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;

public class BasicInfoDto {

	private String employeeId;
	private LocalDate dateOfJoining;
	private int probationPeriod;
	private String employeeType;
	private String workLocation;
	private EmployeeStatus employeeStatus;
	private float workExperience;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public int getProbationPeriod() {
		return probationPeriod;
	}

	public void setProbationPeriod(int probationPeriod) {
		this.probationPeriod = probationPeriod;
	}

	public String getEmployeeType() {
		return employeeType;
	}

	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public EmployeeStatus getEmployeeStatus() {
		return employeeStatus;
	}

	public void setEmployeeStatus(EmployeeStatus employeeStatus) {
		this.employeeStatus = employeeStatus;
	}

	public float getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(float workExperience) {
		this.workExperience = workExperience;
	}

}
