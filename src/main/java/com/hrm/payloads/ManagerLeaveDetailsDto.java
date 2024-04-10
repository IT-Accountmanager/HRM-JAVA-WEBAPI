package com.hrm.payloads;

import java.time.LocalDate;
import java.time.Month;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.LeaveType;

public class ManagerLeaveDetailsDto {
	private int id;
	private Month month;
	private String employeeId;
	private String employeeName;
	private Departments.Department subDepartment;
	private LeaveType leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Double appliedDaysForLeave;
	private Double approvedDaysForLeave;
	private String remarks;

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

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
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

	public Double getAppliedDaysForLeave() {
		return appliedDaysForLeave;
	}

	public void setAppliedDaysForLeave(Double appliedDaysForLeave) {
		this.appliedDaysForLeave = appliedDaysForLeave;
	}

	public Double getApprovedDaysForLeave() {
		return approvedDaysForLeave;
	}

	public void setApprovedDaysForLeave(Double approvedDaysForLeave) {
		this.approvedDaysForLeave = approvedDaysForLeave;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
