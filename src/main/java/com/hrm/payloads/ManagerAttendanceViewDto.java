package com.hrm.payloads;

import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

public class ManagerAttendanceViewDto {
	private Month month;
	private String employeeId;
	private String employeeName;
	private Department department;
	private Long presentDays;
	private double approvedHoursForBilling;
	
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
	

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public Long getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(Long presentDays) {
		this.presentDays = presentDays;
	}

	public double getApprovedHoursForBilling() {
		return approvedHoursForBilling;
	}

	public void setApprovedHoursForBilling(double approvedHoursForBilling) {
		this.approvedHoursForBilling = approvedHoursForBilling;
	}

	

//	public String getMonthlyApprovedHoursForBilling() {
//		return monthlyApprovedHoursForBilling;
//	}
//
//	public void setMonthlyApprovedHoursForBilling(String monthlyApprovedHoursForBilling) {
//		this.monthlyApprovedHoursForBilling = monthlyApprovedHoursForBilling;
//	}
	
	




	

}
