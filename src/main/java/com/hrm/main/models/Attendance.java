package com.hrm.main.models;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
import com.hrm.main.models.Helper.EnumCollection.AttendanceStatus;
import com.hrm.main.models.Helper.EnumCollection.Half;
import com.hrm.main.models.Helper.EnumCollection.LeaveType;
import com.hrm.main.payloads.AttendanceEmployeeDto;

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
	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private Duration workHrs;
	private AttendanceStatus attendanceStatus = AttendanceStatus.Anomaly;
	private LeaveType leaveType;
	private String projectId;
	private float appliedHrsForBilling;
	private float approvedHrsForBilling;
	private float regularizedHours;
	private ApprovalStatus status;
	private LocalDate startDate;
	private LocalDate endDate;
	private String leaveReason;
//<<<<<<< HEAD
	private float productionHours;
	private float otherHours;
	private float totalHours;
	private LocalTime exatctInTime;
	private LocalTime exactOutTime;
	private Duration regularisationRequestHours;
	private String regularisationReason;
//=======
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

	public float getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(float productionHours) {
		this.productionHours = productionHours;
	}

	public float getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(float otherHours) {
		this.otherHours = otherHours;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

	public LocalTime getExatctInTime() {
		return exatctInTime;
	}

	public void setExatctInTime(LocalTime exatctInTime) {
		this.exatctInTime = exatctInTime;
	}

	public LocalTime getExactOutTime() {
		return exactOutTime;
	}

	public void setExactOutTime(LocalTime exactOutTime) {
		this.exactOutTime = exactOutTime;
	}

	public Duration getRegularisationRequestHours() {
		return regularisationRequestHours;
	}

	public void setRegularisationRequestHours(Duration regularisationRequestHours) {
		this.regularisationRequestHours = regularisationRequestHours;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

}
