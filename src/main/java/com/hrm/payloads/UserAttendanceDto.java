package com.hrm.payloads;

import java.time.LocalTime;
import java.time.Month;
import java.time.Duration;

public class UserAttendanceDto {

	private String employeeId;
	private String month;
	private String date;
	private LocalTime inTime;
	private LocalTime outTime;
	private Long workHrs;
	private LocalTime requestedInTime;
	private LocalTime requestedOutTime;
	private Long requestedWorkHrs;
	private char attendanceStatus;
	private String manager;
	private String projectId;
	private int appliedHrsForBilling;
	private int approvedHrsForBilling;
	private Double billableDays;
	private String remarks;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

	public Long getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(Long workHrs) {
		this.workHrs = workHrs;
	}

	public LocalTime getRequestedInTime() {
		return requestedInTime;
	}

	public void setRequestedInTime(LocalTime requestedInTime) {
		this.requestedInTime = requestedInTime;
	}

	public LocalTime getRequestedOutTime() {
		return requestedOutTime;
	}

	public void setRequestedOutTime(LocalTime requestedOutTime) {
		this.requestedOutTime = requestedOutTime;
	}

	public Long getRequestedWorkHrs() {
		return requestedWorkHrs;
	}

	public void setRequestedWorkHrs(Long requestedWorkHrs) {
		this.requestedWorkHrs = requestedWorkHrs;
	}

	public char getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(char attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public int getAppliedHrsForBilling() {
		return appliedHrsForBilling;
	}

	public void setAppliedHrsForBilling(int appliedHrsForBilling) {
		this.appliedHrsForBilling = appliedHrsForBilling;
	}

	public int getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}

	public void setApprovedHrsForBilling(int approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	public Double getBillableDays() {
		return billableDays;
	}

	public void setBillableDays(Double billableDays) {
		this.billableDays = billableDays;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
