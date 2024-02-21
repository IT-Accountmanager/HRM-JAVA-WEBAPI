package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.NoticePeriod;
import com.hrm.main.models.Helper.EnumCollection.ResignationStatus;

public class UResignationEditDto {

	private LocalDate resignationDate;

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

}
