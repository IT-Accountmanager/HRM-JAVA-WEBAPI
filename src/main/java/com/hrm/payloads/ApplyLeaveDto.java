package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.Helper.EnumCollection.AttendanceStatus;
import com.hrm.Helper.EnumCollection.Half;
import com.hrm.Helper.EnumCollection.LeaveType;

public class ApplyLeaveDto {
	private String employeeId;
	private LeaveType leaveType;
	private LocalDate startDate;
	private LocalDate endDate;
	private Half half1;
	private Half half2;
	private AttendanceStatus attendanceStatus = AttendanceStatus.Anomaly;
	private String leaveReason;
	
	
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}
	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	
	
	
}
