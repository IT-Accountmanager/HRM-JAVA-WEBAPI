package com.hrm.payloads;

import java.time.LocalDate;
import java.time.LocalTime;

public class RegularizationHoursDto {

	private LocalDate date;
	private LocalTime requestedInTime;
	private LocalTime requestedOutTime;
	private Long requestedWorkHrs;
	private String regularisationReason;

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getRequestedInTime() {
		return requestedInTime;
	}

	public void setRequestedInTime(LocalTime requestedInTime) {
		this.requestedInTime = requestedInTime;
	}

	public LocalTime getRequestedOutTime() {
		return requestedOutTime;
	}

	public void setRequestedOutTime(LocalTime requestedOutTime) {
		this.requestedOutTime = requestedOutTime;
	}

	public Long getRequestedWorkHrs() {
		return requestedWorkHrs;
	}

	public void setRequestedWorkHrs(Long requestedWorkHrs) {
		this.requestedWorkHrs = requestedWorkHrs;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

}
