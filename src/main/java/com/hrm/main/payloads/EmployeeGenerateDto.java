package com.hrm.main.payloads;

import java.time.LocalDate;

public class EmployeeGenerateDto {

	private String employeeId;
	private String name;
	private String designation;
	private int workingMonths;
	private float ctc;
	private LocalDate dateOfJoining;
	private LocalDate dteOfReleasing;

	public EmployeeGenerateDto() {
		super();
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public int getWorkingMonths() {
		return workingMonths;
	}

	public void setWorkingMonths(int workingMonths) {
		this.workingMonths = workingMonths;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public LocalDate getDteOfReleasing() {
		return dteOfReleasing;
	}

	public void setDteOfReleasing(LocalDate dteOfReleasing) {
		this.dteOfReleasing = dteOfReleasing;
	}

}
