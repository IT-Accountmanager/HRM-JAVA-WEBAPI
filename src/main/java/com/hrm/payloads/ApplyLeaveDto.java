package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;

public class ApplyLeaveDto {

	private LeaveType leaveType;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private Half half1;
	private Half half2;
	private String managerId;
	private String leaveReason;
	private String employeeId;

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public LocalDate getLeaveStartDate() {
		return leaveStartDate;
	}

	public void setLeaveStartDate(LocalDate leaveStartDate) {
		this.leaveStartDate = leaveStartDate;
	}

	public LocalDate getLeaveEndDate() {
		return leaveEndDate;
	}

	public void setLeaveEndDate(LocalDate leaveEndDate) {
		this.leaveEndDate = leaveEndDate;
	}

	public Half getHalf1() {
		return half1;
	}

	public void setHalf1(Half half1) {
		this.half1 = half1;
	}

	public Half getHalf2() {
		return half2;
	}

	public void setHalf2(Half half2) {
		this.half2 = half2;
	}

	public String getManagerId() {
		return managerId;
	}

	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

}
