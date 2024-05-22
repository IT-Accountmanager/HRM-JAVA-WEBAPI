package com.hrm.payloads;

public class ManagerAttendanceSummaryDto {

	private String month;
	private Long noOfTeamMember;
	private Integer workingDays;
	private Integer PresentDays;
	private Double leaves;
	private Integer totalDays;
	private Integer lop;
	private Long approvedBillableHours;

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getNoOfTeamMember() {
		return noOfTeamMember;
	}

	public void setNoOfTeamMember(Long noOfTeamMember) {
		this.noOfTeamMember = noOfTeamMember;
	}

	public Integer getWorkingDays() {
		return workingDays;
	}

	public void setWorkingDays(Integer workingDays) {
		this.workingDays = workingDays;
	}

	public Integer getPresentDays() {
		return PresentDays;
	}

	public void setPresentDays(Integer presentDays) {
		PresentDays = presentDays;
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
