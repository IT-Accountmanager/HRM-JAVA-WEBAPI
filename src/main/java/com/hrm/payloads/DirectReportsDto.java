package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;

public class DirectReportsDto {

	private String name;
	private Departments.Department subDepartment;
	private Designation designation;
	private LocalDate from;
	private LocalDate to;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public LocalDate getFrom() {
		return from;
	}

	public void setFrom(LocalDate from) {
		this.from = from;
	}

	public LocalDate getTo() {
		return to;
	}

	public void setTo(LocalDate to) {
		this.to = to;
	}

}
