package com.hrm.payloads;

import java.time.LocalDate;
import java.util.List;

public class BillableHoursDto {
// private List<LocalDate> listOfDate;
private LocalDate date;
private double productionHours;
private double otherHours;
private double appliedHrsForBilling;

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

public double getProductionHours() {
	return productionHours;
}

public void setProductionHours(double productionHours) {
	this.productionHours = productionHours;
}

public double getOtherHours() {
	return otherHours;
}

public void setOtherHours(double otherHours) {
	this.otherHours = otherHours;
}

public double getAppliedHrsForBilling() {
	return appliedHrsForBilling;
}

public void setAppliedHrsForBilling(double appliedHrsForBilling) {
	this.appliedHrsForBilling = appliedHrsForBilling;
}


}
