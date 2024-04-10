package com.hrm.payloads;

public class ManagerLeaveEditDto {

	private String remarks;
	private double approvedDaysForLeave;

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public double getApprovedDaysForLeave() {
		return approvedDaysForLeave;
	}

	public void setApprovedDaysForLeave(double approvedDaysForLeave) {
		this.approvedDaysForLeave = approvedDaysForLeave;
	}

}
