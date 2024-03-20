package com.hrm.payloads;

import java.time.LocalDate;
import java.util.List;

public class BillableHoursDto {
// private List<LocalDate> listOfDate;
private LocalDate date;
private String productionHours;
private String otherHours;
private String appliedHrsForBilling;

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

public String getAppliedHrsForBilling() {
	return appliedHrsForBilling;
}

public void setAppliedHrsForBilling(String appliedHrsForBilling) {
	this.appliedHrsForBilling = appliedHrsForBilling;
}


}
