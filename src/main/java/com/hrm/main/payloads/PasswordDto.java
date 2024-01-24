package com.hrm.main.payloads;

public class PasswordDto {

	private String password;

	public PasswordDto() {
		super();
	}

	public PasswordDto(String password) {
		super();
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
