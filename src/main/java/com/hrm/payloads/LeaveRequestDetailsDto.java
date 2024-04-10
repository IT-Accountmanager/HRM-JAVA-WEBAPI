package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;

public class LeaveRequestDetailsDto {

	private Byte[] profilePicture;
	private String name;
	private Departments.Department subDepartment;
	private Designation designation;
	private LeaveType leaveType;
	private float balence;
	private LocalDate startDate;
	private LocalDate endDate;
	private int totalDays;
	private Half firsHalf;
	private Half secondHalf;
	private String reason;
	private String manager;

	public Byte[] getProfilePicture() {
		return profilePicture;
	}

	public void setProfilePicture(Byte[] profilePicture) {
		this.profilePicture = profilePicture;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Departments.Department getSubDepartment() {
		return subDepartment;
	}

	public void setSubDepartment(Departments.Department subDepartment) {
		this.subDepartment = subDepartment;
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

	public float getBalence() {
		return balence;
	}

	public void setBalence(float balence) {
		this.balence = balence;
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

	public int getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(int totalDays) {
		this.totalDays = totalDays;
	}

	public Half getFirsHalf() {
		return firsHalf;
	}

	public void setFirsHalf(Half firsHalf) {
		this.firsHalf = firsHalf;
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

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

}
