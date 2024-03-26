package com.hrm.models;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveStatus;
import com.hrm.helper.EnumCollection.LeaveType;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class LeaveManagementTable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String employeeId;
	private LocalDate leaveStartDate;
	private LocalDate leaveEndDate;
	private float numberOfDays;
	private LeaveType leaveType;
	private String managerId;
	private LeaveStatus statusOfLeave =LeaveStatus.PENDING;
	private String comments;
	private Half half1;
	private Half half2;
	private String leaveReason;
	
	private double appliedDaysForLeave;
	
	
	private double approvedDaysForLeave;

	
	
	
	
	
	public double getAppliedDaysForLeave() {
		return appliedDaysForLeave;
	}
	public void setAppliedDaysForLeave(double appliedDaysForLeave) {
		this.appliedDaysForLeave = appliedDaysForLeave;
	}
	public double getApprovedDaysForLeave() {
		return approvedDaysForLeave;
	}
	public void setApprovedDaysForLeave(double approvedDaysForLeave) {
		this.approvedDaysForLeave = approvedDaysForLeave;
	}
	public void setApprovedDaysForLeave(float approvedDaysForLeave) {
		this.approvedDaysForLeave = approvedDaysForLeave;
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
	public String getLeaveReason() {
		return leaveReason;
	}
	public void setLeaveReason(String leaveReason) {
		this.leaveReason = leaveReason;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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
	public float getNumberOfDays() {
		return numberOfDays;
	}
	public void setNumberOfDays(float numberOfDays) {
		this.numberOfDays = numberOfDays;
	}
	public LeaveType getLeaveType() {
		return leaveType;
	}
	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}
	public String getManagerId() {
		return managerId;
	}
	public void setManagerId(String managerId) {
		this.managerId = managerId;
	}
	public LeaveStatus getStatusOfLeave() {
		return statusOfLeave;
	}
	public void setStatusOfLeave(LeaveStatus statusOfLeave) {
		this.statusOfLeave = statusOfLeave;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	
	
}
