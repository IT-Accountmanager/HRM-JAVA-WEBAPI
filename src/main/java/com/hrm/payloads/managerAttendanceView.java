package com.hrm.payloads;

import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;

public class managerAttendanceView {
	private Month month;
	private String employeeId;
	private String employeeName;
	private Departments.Department Department;
	private String presentDays;
	private String approvedHoursForBilling;

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Departments.Department getDepartment() {
		return Department;
	}

	public void setDepartment(Departments.Department department) {
		Department = department;
	}

	public String getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(String presentDays) {
		this.presentDays = presentDays;
	}

	public String getApprovedHoursForBilling() {
		return approvedHoursForBilling;
	}

	public void setApprovedHoursForBilling(String approvedHoursForBilling) {
		this.approvedHoursForBilling = approvedHoursForBilling;
	}

}
