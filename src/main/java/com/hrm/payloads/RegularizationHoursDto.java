package com.hrm.payloads;

import java.time.Duration;
import java.time.LocalTime;

public class RegularizationHoursDto {

	private LocalTime exatctInTime;
	private LocalTime exactOutTime;
	private Duration regularisationRequestHours;
	private String regularisationReason;

	public LocalTime getExatctInTime() {
		return exatctInTime;
	}

	public void setExatctInTime(LocalTime exatctInTime) {
		this.exatctInTime = exatctInTime;
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
