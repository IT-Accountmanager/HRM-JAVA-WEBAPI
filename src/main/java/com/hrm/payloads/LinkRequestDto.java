package com.hrm.payloads;

public class LinkRequestDto {
	private String phoneNumber;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public LinkRequestDto() {
		super();
	}

	public LinkRequestDto(String phoneNumber) {
		super();
		this.phoneNumber = phoneNumber;
	}

}
