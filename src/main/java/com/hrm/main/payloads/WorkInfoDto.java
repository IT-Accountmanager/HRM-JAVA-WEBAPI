package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Designation;
import com.hrm.main.models.Helper.EnumCollection.WorkLocation;

public class WorkInfoDto {

	private Designation designation;
	private Departments department;
	private Departments.Department subDepartment;
	// private String assignTo;
	private WorkLocation workLocation;

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	/*
	 * public String getAssignTo() { return assignTo; }
	 * 
	 * public void setAssignTo(String assignTo) { this.assignTo = assignTo; }
	 */

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

}
