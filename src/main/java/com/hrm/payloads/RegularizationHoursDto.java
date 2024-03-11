package com.hrm.payloads;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;

public class RegularizationHoursDto {

	private LocalDate date;
    private LocalTime exactInTime;
	private LocalTime exactOutTime;
	private Duration regularisationRequestHours;
	private String regularisationReason;
	
	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getExactInTime() {
		return exactInTime;
	}

	public void setExactInTime(LocalTime exactInTime) {
		this.exactInTime = exactInTime;
	}

	public LocalTime getExactOutTime() {
		return exactOutTime;
	}

	public void setExactOutTime(LocalTime exactOutTime) {
		this.exactOutTime = exactOutTime;
	}

	public Duration getRegularisationRequestHours() {
		return regularisationRequestHours;
	}

	public void setRegularisationRequestHours(Duration regularisationRequestHours) {
		this.regularisationRequestHours = regularisationRequestHours;
	}

	public String getRegularisationReason() {
		return regularisationReason;
	}

	public void setRegularisationReason(String regularisationReason) {
		this.regularisationReason = regularisationReason;
	}

}
