package com.hrm.payloads;

import java.time.LocalDate;

public class ManagerAttendanceViewDto {
	
	private LocalDate date;
	private String approvedHrsForBilling;
	private String remarks;
	

	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
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
