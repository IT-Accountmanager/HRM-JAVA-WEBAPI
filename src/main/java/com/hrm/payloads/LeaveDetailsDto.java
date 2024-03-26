package com.hrm.payloads;

import java.time.LocalDate;
import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.LeaveType;

public class LeaveDetailsDto {
	private Month month;
	private Departments department;
	private LeaveType leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private float appliedDaysForLeave;
	private float approvedDaysForLeave;
	private String remarks;
	
	
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}
	public Departments getDepartment() {
		return department;
	}
	public void setDepartment(Departments department) {
		this.department = department;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public float getAppliedDaysForLeave() {
		return appliedDaysForLeave;
	}
	public void setAppliedDaysForLeave(float appliedDaysForLeave) {
		this.appliedDaysForLeave = appliedDaysForLeave;
	}
	public float getApprovedDaysForLeave() {
		return approvedDaysForLeave;
	}
	public void setApprovedDaysForLeave(float approvedDaysForLeave) {
		this.approvedDaysForLeave = approvedDaysForLeave;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
