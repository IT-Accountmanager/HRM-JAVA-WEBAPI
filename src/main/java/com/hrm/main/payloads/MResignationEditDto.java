package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.NoticePeriod;
import com.hrm.main.models.Helper.EnumCollection.ResignationStatus;

public class MResignationEditDto {

	private ResignationStatus resignationStatus;
	private LocalDate resignationDate;
	private LocalDate lastWorkingDay;
	private NoticePeriod noticePeriod;

	public LocalDate getResignationDate() {
		return resignationDate;
	}

	public void setResignationDate(LocalDate resignationDate) {
		this.resignationDate = resignationDate;
	}

	public ResignationStatus getResignationStatus() {
		return resignationStatus;
	}

	public void setResignationStatus(ResignationStatus resignationStatus) {
		this.resignationStatus = resignationStatus;
	}

	public LocalDate getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(LocalDate lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

	public NoticePeriod getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(NoticePeriod noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

}
