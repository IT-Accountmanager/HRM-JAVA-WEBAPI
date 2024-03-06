package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.CategoryControl;
import com.hrm.Helper.EnumCollection.Departments;
import com.hrm.Helper.EnumCollection.Designation;
import com.hrm.Helper.EnumCollection.WorkLocation;

public class WorkInfoDto {

	private Designation designation;
	private Departments department;
	private Departments.Department subDepartment;
	private CategoryControl categoryControl;
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

	public CategoryControl getCategoryControl() {
		return categoryControl;
	}

	public void setCategoryControl(CategoryControl categoryControl) {
		this.categoryControl = categoryControl;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
	}

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

}
