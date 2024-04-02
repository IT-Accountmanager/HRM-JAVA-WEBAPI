package com.hrm.payloads;

import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;

public class ManagerAttendanceViewDto {
	private Month month;
	private String employeeId;
	private String employeeName;
	private Departments department;
	private String presentDays;
	private String monthlyAppliedHoursForBilling;
	
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
	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public String getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(String presentDays) {
		this.presentDays = presentDays;
	}

	public String getMonthlyAppliedHoursForBilling() {
		return monthlyAppliedHoursForBilling;
	}

	public void setMonthlyAppliedHoursForBilling(String monthlyAppliedHoursForBilling) {
		this.monthlyAppliedHoursForBilling = monthlyAppliedHoursForBilling;
	}



	

}
