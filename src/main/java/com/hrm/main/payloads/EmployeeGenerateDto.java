package com.hrm.main.payloads;

import java.time.LocalDate;

public class EmployeeGenerateDto {

	private String employeeId;
	private long candidateId;
	private String name;
	private String workLocation;
	private String designation;
	private float bondPeriod;
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

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
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
