package com.hrm.payloads;

import com.hrm.helper.EnumCollection.Departments;

public class SubDepartmentAndName {
	private Departments.Department subDepartment;
	private String name;

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
