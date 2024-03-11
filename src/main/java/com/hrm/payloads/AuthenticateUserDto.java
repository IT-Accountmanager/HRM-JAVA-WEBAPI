package com.hrm.payloads;

public class AuthenticateUserDto {

	private Long candidateId;
	private String password;
	private String username;
	/*
	 * private String employeeId; private String name;
	 */

	public Long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	/*
	 * public String getEmployeeId() { return employeeId; }
	 * 
	 * public void setEmployeeId(String employeeId) { this.employeeId = employeeId;
	 * }
	 * 
	 * public String getName() { return name; }
	 * 
	 * public void setName(String name) { this.name = name; }
	 */

}
