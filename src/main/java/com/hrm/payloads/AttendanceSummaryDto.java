package com.hrm.payloads;

public class AttendanceSummaryDto {

	private String employeeId;
	private String employeeName;
	private Integer month;
	private String manager;
	private Integer workingDays;
	private Integer presentDays;
	private Double leaves;
	private Integer totalDays;
	private Integer lop;
	private Long approvedBillableHours;

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public String getManager() {
		return manager;
	}

	public void setManager(String manager) {
		this.manager = manager;
	}

	public Integer getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Integer workingDays) {
		this.workingDays = workingDays;
	}

	public Integer getPresentDays() {
		return presentDays;
	}

	public void setPresentDays(Integer presentDays) {
		this.presentDays = presentDays;
	}

	public Double getLeaves() {
		return leaves;
	}

	public void setLeaves(Double leaves) {
		this.leaves = leaves;
	}

	public Integer getTotalDays() {
		return totalDays;
	}

	public void setTotalDays(Integer totalDays) {
		this.totalDays = totalDays;
	}

	public Integer getLop() {
		return lop;
	}

	public void setLop(Integer lop) {
		this.lop = lop;
	}

	public Long getApprovedBillableHours() {
		return approvedBillableHours;
	}

	public void setApprovedBillableHours(Long approvedBillableHours) {
		this.approvedBillableHours = approvedBillableHours;
	}

}
