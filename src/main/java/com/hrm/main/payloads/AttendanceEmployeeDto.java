package com.hrm.main.payloads;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;
import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.AttendanceStatus;

public class AttendanceEmployeeDto {

	private String employeeId;
	private Month month;
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private String workHrs;
	private Duration workHours;
	private AttendanceStatus attendanceStatus;
	private String manager;
	private String projectId;
	private String appliedHoursForBilling;
	private String approvedHoursForBilling;
	private ApprovalStatus billingHoursStatus;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public Month getMonth() {
		return month;
	}

	public void setMonth(Month month) {
		this.month = month;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
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

	public String getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(String workHrs) {
		this.workHrs = workHrs;
	}

	public Duration getWorkHours() {
		return workHours;
	}

	public void setWorkHours(Duration workHours) {
		this.workHours = workHours;
	}

	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
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

	public String getAppliedHoursForBilling() {
		return appliedHoursForBilling;
	}

	public void setAppliedHoursForBilling(String appliedHoursForBilling) {
		this.appliedHoursForBilling = appliedHoursForBilling;
	}

	public String getApprovedHoursForBilling() {
		return approvedHoursForBilling;
	}

	public void setApprovedHoursForBilling(String approvedHoursForBilling) {
		this.approvedHoursForBilling = approvedHoursForBilling;
	}

	public ApprovalStatus getBillingHoursStatus() {
		return billingHoursStatus;
	}

	public void setBillingHoursStatus(ApprovalStatus billingHoursStatus) {
		this.billingHoursStatus = billingHoursStatus;
	}

	public Duration calculateWorkHours() {
		if (inTime != null && outTime != null) {
			Duration workHours = Duration.between(inTime, outTime);
			return workHours;
		} else {
			return Duration.ZERO;
		}
	}

	public String getManagerOrDefault(String defaultValue) {
		return Optional.ofNullable(manager).orElse(defaultValue);
	}

	public String formatWorkHours() {
		if (workHours != null) {
			long hours = workHours.toHours();
			long minutes = workHours.toMinutesPart();
			double seconds = workHours.toSecondsPart();
			return String.format("%dH %dM %.3fS", hours, minutes, seconds);
		} else {
			return "N/A"; // or another default value or handle the null case as needed
		}
	}

}
