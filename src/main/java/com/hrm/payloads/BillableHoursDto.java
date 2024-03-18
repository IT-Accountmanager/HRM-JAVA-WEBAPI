package com.hrm.payloads;

import java.time.LocalDate;
import java.util.List;

public class BillableHoursDto {
	private LocalDate date;
	private String productionHours;
	private String otherHours;
	private String totalHours;
//
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getProductionHours() {
		return productionHours;
	}

	public void setProductionHours(String productionHours) {
		this.productionHours = productionHours;
	}

	public String getOtherHours() {
		return otherHours;
	}

	public void setOtherHours(String otherHours) {
		this.otherHours = otherHours;
	}

	public String getTotalHours() {
		return totalHours;
	}

	public void setTotalHours(String totalHours) {
		this.totalHours = totalHours;
	}

	

}
