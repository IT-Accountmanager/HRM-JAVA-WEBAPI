package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Designation;

public class WorkInfoDto {

	private Designation designation;
	private Departments department;
	private Departments.Department subDepartment;
	// private String assignTo;
	private String workLocation;
	private long candidateId;

	public Designation getDesignation() {
		return designation;
	}
	
	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public long getCandidateId() {
		return candidateId;
	}


	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
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

	public String getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(String workLocation) {
		this.workLocation = workLocation;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}
	

}
