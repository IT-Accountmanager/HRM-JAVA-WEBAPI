package com.hrm.payloads;

import java.time.LocalDate;
import java.util.List;

public class BillableHoursDto {
// private List<LocalDate> listOfDate;
	private LocalDate date;
	private int productionHours;
	private int otherHours;
	private int appliedHrsForBilling;

// public List<LocalDate> getListOfDate() {
// return listOfDate;
// }
//
// public void setListOfDate(List<LocalDate> listOfDate) {
// this.listOfDate = listOfDate;
// }

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public int getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(int productionHours) {
		this.productionHours = productionHours;
	}

	public int getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(int otherHours) {
		this.otherHours = otherHours;
	}

	public int getAppliedHrsForBilling() {
		return appliedHrsForBilling;
	}

	public void setAppliedHrsForBilling(int appliedHrsForBilling) {
		this.appliedHrsForBilling = appliedHrsForBilling;
	}

}
