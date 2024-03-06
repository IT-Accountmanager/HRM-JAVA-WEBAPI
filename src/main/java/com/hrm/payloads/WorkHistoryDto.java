package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.Helper.EnumCollection.Departments;
import com.hrm.Helper.EnumCollection.Designation;

public class WorkHistoryDto {
	private Departments.Department subDepartment;
	private Designation designation;
	private String projectManager;
	private LocalDate from;
	private LocalDate to;

	
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

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
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
