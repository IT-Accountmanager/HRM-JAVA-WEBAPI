package com.hrm.payloads;

import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;

public class ManagerAttendanceDetailsDto {
	private int id;
	private Month month;
	private String employeeId;
	private String employeeName;
	private Departments.Department department;
	private int presentDays;
	private int approvedHrsForBilling;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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
		return department;
	}

	public void setDepartment(Departments.Department department) {
		this.department = department;
	}

	public int getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(int presentDays) {
		this.presentDays = presentDays;
	}

	public int getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}

	public void setApprovedHrsForBilling(int approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	

}
