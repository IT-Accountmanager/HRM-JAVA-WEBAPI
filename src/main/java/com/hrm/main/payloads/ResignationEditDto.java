package com.hrm.main.payloads;

import java.time.LocalDate;

public class ResignationEditDto {
	private LocalDate resignationDate;

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

}
