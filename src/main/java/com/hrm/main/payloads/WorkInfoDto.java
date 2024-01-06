package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.Departments;

public class WorkInfoDto {

	private String designation;
	private Departments department;
	/*
	 * private Sub_Department subDepartment;
	 */ private String assignTo;
	private String workLocation;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	/*
	 * public Sub_Department getSubDepartment() { return subDepartment; }
	 * 
	 * public void setSubDepartment(Sub_Department subDepartment) {
	 * this.subDepartment = subDepartment; }
	 */

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

}
