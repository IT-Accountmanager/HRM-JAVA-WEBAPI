package com.hrm.payloads;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;

public class LeaveDetailsRequestDto {

	private String managerId;
	private String employeeId;
	private Year year;
	private Month month;

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

}
