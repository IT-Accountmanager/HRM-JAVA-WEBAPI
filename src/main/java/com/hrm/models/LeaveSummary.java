package com.hrm.models;

import java.time.Month;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class LeaveSummary {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String employeeId;
	private Month month;
	private float openingBalance;
	private float appliedLeaves;
	private float carryForward;
	private float totalBalance;
	
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
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}
	public float getOpeningBalance() {
		return openingBalance;
	}
	public void setOpeningBalance(float openingBalance) {
		this.openingBalance = openingBalance;
	}
	public float getAppliedLeaves() {
		return appliedLeaves;
	}
	public void setAppliedLeaves(float appliedLeaves) {
		this.appliedLeaves = appliedLeaves;
	}
	public float getCarryForward() {
		return carryForward;
	}
	public void setCarryForward(float carryForward) {
		this.carryForward = carryForward;
	}
	public float getTotalBalance() {
		return totalBalance;
	}
	public void setTotalBalance(float totalBalance) {
		this.totalBalance = totalBalance;
	}
	
	
}
