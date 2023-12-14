package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.Sub_Department;

public class WorkInfoDto {

	private String designation;
	private String jobTitle;
	private Departments department;
	private Sub_Department subDepartment;
	private String assignTo;

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Sub_Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Sub_Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

}
