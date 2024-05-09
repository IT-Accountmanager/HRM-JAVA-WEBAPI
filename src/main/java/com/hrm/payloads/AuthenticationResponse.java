package com.hrm.payloads;

public class AuthenticationResponse {

	private String username;
	private String status;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public AuthenticationResponse(String username, String status) {
		this.username = username;
		this.status = status;
	}

}
