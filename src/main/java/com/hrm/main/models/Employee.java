package com.hrm.main.models;

import java.time.LocalDate;

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
	private String employeeId;
	private String name;
	private String designation;
	private int workingMonths;
	private float ctc;
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
