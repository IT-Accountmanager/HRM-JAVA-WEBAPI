package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.Helper.EnumCollection.NoticePeriod;
import com.hrm.Helper.EnumCollection.ResignationStatus;

public class UResignationEditDto {

	private LocalDate resignationDate;

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

}
