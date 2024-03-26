package com.hrm.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.AttendanceStatus;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.payloads.AttendanceEmployeeDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Attendance {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attendance_id_seq")
	@SequenceGenerator(name = "attendance_id_seq", initialValue = 1, allocationSize = 1, sequenceName = "attendance_id_seq")
	private int id;

	// DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-mm-yyyy");

	private String employeeId;
	private Month month;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private Duration workHrs;
	private char attendanceStatus;
	private LeaveType leaveType;
	private String projectId;
	private String appliedHrsForBilling;
	private String approvedHrsForBilling;
	private float regularizedHours;
	private ApprovalStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveReason;
	private String productionHours;
	private String otherHours;
	private float totalHours;
	private LocalTime exactInTime;
	private LocalTime exactOutTime;
	private String regularisationRequestHours;
	private String regularisationReason;
	private float monthlyPresentDays;
	private float monthlyLossOfPayDays;
	private Duration monthlyAppliedHoursForBilling;
	private Duration monthlyApprovedHoursForBilling;
	private String remarks;

	
//	edited

//	private float appliedDaysForLeave;
//	private float approvedDaysForLeave;
//	private String leaveRemarks;
	
	

//	public float getAppliedDaysForLeave() {
//		return appliedDaysForLeave;
//	}
//
//	public void setAppliedDaysForLeave(float appliedDaysForLeave) {
//		this.appliedDaysForLeave = appliedDaysForLeave;
//	}
//
//	public float getApprovedDaysForLeave() {
//		return approvedDaysForLeave;
//	}
//
//	public void setApprovedDaysForLeave(float approvedDaysForLeave) {
//		this.approvedDaysForLeave = approvedDaysForLeave;
//	}
//
//	public String getLeaveRemarks() {
//		return leaveRemarks;
//	}
//
//	public void setLeaveRemarks(String leaveRemarks) {
//		this.leaveRemarks = leaveRemarks;
//	}

	

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public float getMonthlyPresentDays() {
		return monthlyPresentDays;
	}

	public void setMonthlyPresentDays(float monthlyPresentDays) {
		this.monthlyPresentDays = monthlyPresentDays;
	}

	public float getMonthlyLossOfPayDays() {
		return monthlyLossOfPayDays;
	}

	public void setMonthlyLossOfPayDays(float monthlyLossOfPayDays) {
		this.monthlyLossOfPayDays = monthlyLossOfPayDays;
	}

	public Duration getMonthlyAppliedHoursForBilling() {
		return monthlyAppliedHoursForBilling;
	}

	public void setMonthlyAppliedHoursForBilling(Duration monthlyAppliedHoursForBilling) {
		this.monthlyAppliedHoursForBilling = monthlyAppliedHoursForBilling;
	}

	public Duration getMonthlyApprovedHoursForBilling() {
		return monthlyApprovedHoursForBilling;
	}

	public void setMonthlyApprovedHoursForBilling(Duration monthlyApprovedHoursForBilling) {
		this.monthlyApprovedHoursForBilling = monthlyApprovedHoursForBilling;
	}

	// =======
	private Half half1;
	private Half half2;

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
//>>>>>>> branch 'ramachandra' of https://github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git

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

	public String getAppliedHrsForBilling() {
		return appliedHrsForBilling;
	}

	public void setAppliedHrsForBilling(String appliedHrsForBilling) {
		this.appliedHrsForBilling = appliedHrsForBilling;
	}

	public String getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}

	public void setApprovedHrsForBilling(String approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	public float getRegularizedHours() {
		return regularizedHours;
	}

	public void setRegularizedHours(float regularizedHours) {
		this.regularizedHours = regularizedHours;
	}

	public char getAttendanceStatus() {
		return attendanceStatus;
	}

	public void setAttendanceStatus(char attendanceStatus) {
		this.attendanceStatus = attendanceStatus;
	}

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getLeaveReason() {
		return leaveReason;
	}

	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public String getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(String productionHours) {
		this.productionHours = productionHours;
	}

	public String getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(String otherHours) {
		this.otherHours = otherHours;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public LocalTime getExactInTime() {
		return exactInTime;
	}

	public void setExactInTime(LocalTime exactInTime) {
		this.exactInTime = exactInTime;
	}

	public LocalTime getExactOutTime() {
		return exactOutTime;
	}

	public void setExactOutTime(LocalTime exactOutTime) {
		this.exactOutTime = exactOutTime;
	}

	public String getRegularisationRequestHours() {
		return regularisationRequestHours;
	}

	public void setRegularisationRequestHours(String regularisationRequestHours) {
		this.regularisationRequestHours = regularisationRequestHours;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

}
