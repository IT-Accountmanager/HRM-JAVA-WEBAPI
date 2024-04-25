package com.hrm.payloads;

import java.time.LocalDate;

public class ManagerAttendanceEditDto {

	private LocalDate date;
	private int approvedHrsForBilling;
	private String remarks;
//	private String monthlyApprovedHoursForBilling;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getApprovedHrsForBilling() {
		return approvedHrsForBilling;
	}

	public void setApprovedHrsForBilling(int approvedHrsForBilling) {
		this.approvedHrsForBilling = approvedHrsForBilling;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
//	public String getMonthlyApprovedHoursForBilling() {
//		return monthlyApprovedHoursForBilling;
//	}
//	public void setMonthlyApprovedHoursForBilling(String monthlyApprovedHoursForBilling) {
//		this.monthlyApprovedHoursForBilling = monthlyApprovedHoursForBilling;
//	}

}
