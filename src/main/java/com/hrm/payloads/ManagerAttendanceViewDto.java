package com.hrm.payloads;

public class ManagerAttendanceViewDto {
	

	private String projectId;
	private String approvedHrsForBilling;
	private String remarks;
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}
	public void setApprovedHrsForBilling(String approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
