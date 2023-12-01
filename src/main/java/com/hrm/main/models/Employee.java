package com.hrm.main.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Employee_Id_Sequence")
	@SequenceGenerator(name = "Employee_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Employee_Id_Sequence")
	private int employeeSn;
	@Column(name = "candidate_id")
	private long candidateId;
	private String employeeId;
	private String name;
	private String workLocation;
	private String designation;
	private float bondPeriod;
	private int ctc;
	private LocalDate dateOfJoining;
	private LocalDate dteOfReleasing;

	public Employee() {
		super();
	}

	public int getEmployeeSn() {
		return employeeSn;
	}

	public void setEmployeeSn(int employeeSn) {
		this.employeeSn = employeeSn;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
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

	public String getWorkLocation() {
		return workLocation;
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

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public int getCtc() {
		return ctc;
	}

	public void setCtc(int ctc) {
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
