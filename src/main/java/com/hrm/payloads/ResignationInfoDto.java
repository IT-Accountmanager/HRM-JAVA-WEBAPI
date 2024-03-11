package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.NoticePeriod;
import com.hrm.helper.EnumCollection.ResignationStatus;

public class ResignationInfoDto {

	private LocalDate resignationDate;
	private ResignationStatus resignationStatus;
	private NoticePeriod noticePeriod;
	private LocalDate lastWorkingDay;

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

	public NoticePeriod getNoticePeriod() {
		return noticePeriod;
	}

	public void setNoticePeriod(NoticePeriod noticePeriod) {
		this.noticePeriod = noticePeriod;
	}

	public LocalDate getLastWorkingDay() {
		return lastWorkingDay;
	}

	public void setLastWorkingDay(LocalDate lastWorkingDay) {
		this.lastWorkingDay = lastWorkingDay;
	}

}
