package com.hrm.payloads;

import java.time.Duration;
import java.time.Month;

public class AttendanceHRManagerDto {
	private String employeeId;
	private String employeeName;
	private Duration totalDays;
	private Month month;
	private Duration presentDays;
	private String lop;

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

	public Duration getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Duration totalDays) {
		this.totalDays = totalDays;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public Duration getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(Duration presentDays) {
		this.presentDays = presentDays;
	}

	public String getLop() {
		return lop;
	}

	public void setLop(String lop) {
		this.lop = lop;
	}

}
