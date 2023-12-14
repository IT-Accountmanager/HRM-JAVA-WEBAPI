package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.Departments;

public class WorkHistoryDto {
	private Departments previouDepartment;
	private String previouDesignation;
	private LocalDate previouWorkFrom;
	private LocalDate previouWorkUpto;

	public Departments getPreviouDepartment() {
		return previouDepartment;
	}

	public void setPreviouDepartment(Departments previouDepartment) {
		this.previouDepartment = previouDepartment;
	}

	public String getPreviouDesignation() {
		return previouDesignation;
	}

	public void setPreviouDesignation(String previouDesignation) {
		this.previouDesignation = previouDesignation;
	}

	public LocalDate getPreviouWorkFrom() {
		return previouWorkFrom;
	}

	public void setPreviouWorkFrom(LocalDate previouWorkFrom) {
		this.previouWorkFrom = previouWorkFrom;
	}

	public LocalDate getPreviouWorkUpto() {
		return previouWorkUpto;
	}

	public void setPreviouWorkUpto(LocalDate previouWorkUpto) {
		this.previouWorkUpto = previouWorkUpto;
	}

}
