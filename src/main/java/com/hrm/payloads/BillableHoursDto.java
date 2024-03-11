package com.hrm.payloads;

import java.time.LocalDate;
import java.util.List;

public class BillableHoursDto {
//	private List<LocalDate> listOfDate;
	private LocalDate date;
	private float productionHours;
	private float otherHours;
	private float totalHours;

//	public List<LocalDate> getListOfDate() {
//		return listOfDate;
//	}
//
//	public void setListOfDate(List<LocalDate> listOfDate) {
//		this.listOfDate = listOfDate;
//	}
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public float getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(float productionHours) {
		this.productionHours = productionHours;
	}

	public float getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(float otherHours) {
		this.otherHours = otherHours;
	}

	public float getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(float totalHours) {
		this.totalHours = totalHours;
	}

}
