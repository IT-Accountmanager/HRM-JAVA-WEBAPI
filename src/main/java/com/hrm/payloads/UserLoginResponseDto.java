package com.hrm.payloads;

import java.time.LocalDate;

public class UserLoginResponseDto {

	private LocalDate duration;
	private String message;
	private long candidateId;
	
	

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public LocalDate getDuration() {
		return duration;
	}

	public void setDuration(LocalDate duration) {
		this.duration = duration;
	}
	
	

}
