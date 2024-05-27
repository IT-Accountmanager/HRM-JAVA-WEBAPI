package com.hrm.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;

import jakarta.persistence.Column;
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

	@Column(name = "employee_id")
	private String employeeId;
	private Month month;
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private Long workHrs;
	private LocalTime requestedInTime;
	private LocalTime requestedOutTime;
	private Long requestedWorkHrs;
	private char attendanceStatus;
	private LeaveType leaveType;
	private String projectId;
	@Column(nullable = true, name = "applied_hrs_for_billing")
	private Integer appliedHrsForBilling;
	@Column(nullable = true, name = "approved_hrs_for_billing")
	private Integer approvedHrsForBilling;
	private Float regularizedHours;
	private ApprovalStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveReason;
	@Column(nullable = true)
	private Integer productionHours;
	@Column(nullable = true)
	private Integer otherHours;
	@Column(nullable = true)
	private Integer totalHours;

	// private LocalTime exactInTime;
	// private LocalTime exactOutTime;
	private Long regularisationRequestHours;

	/*
	 * private LocalTime exactInTime; private LocalTime exactOutTime; private
	 * Duration regularisationRequestHours;
	 */
	private String regularisationReason;
	private Float monthlyPresentDays;
	private Float monthlyLossOfPayDays;
	private String monthlyAppliedHoursForBilling;
	private String monthlyApprovedHoursForBilling;
	private String remarks;
	private String billableAttendanceStatus;

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

	public String getMonthlyAppliedHoursForBilling() {
		return monthlyAppliedHoursForBilling;
	}

	public void setMonthlyAppliedHoursForBilling(String monthlyAppliedHoursForBilling) {
		this.monthlyAppliedHoursForBilling = monthlyAppliedHoursForBilling;
	}

	public String getMonthlyApprovedHoursForBilling() {
		return monthlyApprovedHoursForBilling;
	}

	public void setMonthlyApprovedHoursForBilling(String monthlyApprovedHoursForBilling) {
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

	public String getProjectId() {
		return projectId;
	}

	public void setProjectId(String projectId) {
		this.projectId = projectId;
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

	public void setRegularizedHours(Float regularizedHours) {
		this.regularizedHours = regularizedHours;
	}

	public void setMonthlyPresentDays(Float monthlyPresentDays) {
		this.monthlyPresentDays = monthlyPresentDays;
	}

	public void setMonthlyLossOfPayDays(Float monthlyLossOfPayDays) {
		this.monthlyLossOfPayDays = monthlyLossOfPayDays;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

	public Long getRegularisationRequestHours() {
		return regularisationRequestHours;
	}

	public void setRegularisationRequestHours(Long regularisationRequestHours) {
		this.regularisationRequestHours = regularisationRequestHours;
	}

	public Integer getAppliedHrsForBilling() {
		return appliedHrsForBilling != null ? appliedHrsForBilling : 0; // Return a default value if
																		// appliedHrsForBilling is null
	}

	public void setAppliedHrsForBilling(Integer appliedHrsForBilling) {
		Optional.ofNullable(appliedHrsForBilling).filter(min -> min >= 0 && min <= 480)
				.orElseThrow(() -> new IllegalArgumentException("Applied hours for billing must be between 0 and 8"));
		this.appliedHrsForBilling = appliedHrsForBilling;
	}

	public Integer getApprovedHrsForBilling() {
		return approvedHrsForBilling != null ? approvedHrsForBilling : 0; // Return a default value if
																			// appliedHrsForBilling is null
	}

	public void setApprovedHrsForBilling(Integer approvedHrsForBilling) {
		Optional.ofNullable(approvedHrsForBilling).filter(min -> min >= 0 && min <= 480)
				.orElseThrow(() -> new IllegalArgumentException("Approved hours for billing must be between 0 and 8"));
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	public int getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(int productionHours) {
		this.productionHours = productionHours;
	}

	public int getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(int otherHours) {
		this.otherHours = otherHours;
	}

	public int getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(int totalHours) {
		this.totalHours = totalHours;
	}

	public Long getWorkHrs() {
		return workHrs;
	}

	public void setWorkHrs(Long workHrs) {
		this.workHrs = workHrs;
	}

	public String getBillableAttendanceStatus() {
		return billableAttendanceStatus;
	}

	public void setBillableAttendanceStatus(String billableAttendanceStatus) {
		this.billableAttendanceStatus = billableAttendanceStatus;
	}

}