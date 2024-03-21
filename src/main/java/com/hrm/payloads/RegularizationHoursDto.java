package com.hrm.payloads;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegularizationHoursDto {

	private LocalDate date;
	private LocalTime inTime;
	private LocalTime outTime;
	private String regularisationRequestHours;
	private String regularisationReason;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getInTime() {
		return inTime;
	}

	public void setInTime(LocalTime inTime) {
		this.inTime = inTime;
	}

	public LocalTime getOutTime() {
		return outTime;
	}

	public void setOutTime(LocalTime outTime) {
		this.outTime = outTime;
	}

	public String getRegularisationRequestHours() {
		return regularisationRequestHours;
	}

	public void setRegularisationRequestHours(String regularisationRequestHours) {
		this.regularisationRequestHours = regularisationRequestHours;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

}
