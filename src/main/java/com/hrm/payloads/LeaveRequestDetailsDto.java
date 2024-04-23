package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;

public class LeaveRequestDetailsDto {

	private byte[] profilePicture;
	private Departments department;
	private Designation designation;
	private LeaveType leaveType;
	private float balance;
	private LocalDate startDate;
	private LocalDate endDate;
	private double appliedDaysForLeave;
	private String manager;
	private String name;
	
	
	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public double getAppliedDaysForLeave() {
		return appliedDaysForLeave;
	}

	public void setAppliedDaysForLeave(double appliedDaysForLeave) {
		this.appliedDaysForLeave = appliedDaysForLeave;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	private Half firstHalf;
	private Half secondHalf;
	private String reason;
	

	

	public byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public Departments getDepartment() {
		return department;
	}

	public void setDepartment(Departments department) {
		this.department = department;
	}

	public Designation getDesignation() {
		return designation;
	}

	public void setDesignation(Designation designation) {
		this.designation = designation;
	}

	public LeaveType getLeaveType() {
		return leaveType;
	}

	public void setLeaveType(LeaveType leaveType) {
		this.leaveType = leaveType;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalence(float balance) {
		this.balance = balance;
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

	public Half getFirstHalf() {
		return firstHalf;
	}

	public void setFirstHalf(Half firstHalf) {
		this.firstHalf = firstHalf;
	}

	public Half getSecondHalf() {
		return secondHalf;
	}

	public void setSecondHalf(Half secondHalf) {
		this.secondHalf = secondHalf;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
