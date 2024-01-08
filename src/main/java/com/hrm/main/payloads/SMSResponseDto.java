package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.SmsStatus;

public class SMSResponseDto {

	private SmsStatus status;
	private String message;

	public SmsStatus getStatus() {
		return status;
	}

	public void setStatus(SmsStatus status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public SMSResponseDto(SmsStatus status, String message) {
		super();
		this.status = status;
		this.message = message;
	}

	public SMSResponseDto() {
		super();
	}

}
