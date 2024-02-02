package com.hrm.main.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.AttendanceStatus;
import com.hrm.main.payloads.AttendanceEmployeeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Attendance {
	/*
	 * @Id
	 * 
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "attendance_id_seq")
	 * 
	 * @SequenceGenerator(name = "attendance_id_seq", initialValue = 1,
	 * allocationSize = 1, sequenceName = "attendance_id_seq") private int id;
	 */
	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");
	@Id
	private String employeeId;
	private Month month;
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private Duration workHrs;
	private AttendanceStatus attendanceStatus = AttendanceStatus.Anomaly;
	private String projectId;
	private float appliedHrsForBilling;
	private float approvedHrsForBilling;
	private float regularizedHours;
	private ApprovalStatus status;

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

	public Duration getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(Duration workHrs) {
		this.workHrs = workHrs;
	}

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}

	public float getAppliedHrsForBilling() {
		return appliedHrsForBilling;
	}

	public void setAppliedHrsForBilling(float appliedHrsForBilling) {
		this.appliedHrsForBilling = appliedHrsForBilling;
	}

	public float getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}

	public void setApprovedHrsForBilling(float approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	public float getRegularizedHours() {
		return regularizedHours;
	}

	public void setRegularizedHours(float regularizedHours) {
		this.regularizedHours = regularizedHours;
	}

	public AttendanceStatus getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(AttendanceStatus attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

}
