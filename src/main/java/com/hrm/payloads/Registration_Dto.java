package com.hrm.payloads;

import com.hrm.models.Onboarding;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class Registration_Dto {
	
	private String emailIdOrMobileNumber;
	private String password;
	@ManyToOne
	@JoinColumn(name = "canadidate_id")
	private Onboarding candidateId;
	
	
	public String getEmailIdOrMobileNumber() {
		return emailIdOrMobileNumber;
	}
	public void setEmailIdOrMobileNumber(String emailIdOrMobileNumber) {
		this.emailIdOrMobileNumber = emailIdOrMobileNumber;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Onboarding getCandidateId() {
		return candidateId;
	}
	public void setCandidateId(Onboarding candidateId) {
		this.candidateId = candidateId;
	}
	
	
	public Registration_Dto(String emailIdOrMobileNumber, String password, Onboarding candidateId) {
		super();
		this.emailIdOrMobileNumber = emailIdOrMobileNumber;
		this.password = password;
		this.candidateId = candidateId;
	}
	
	
	public Registration_Dto() {
		super();
	}
	
	
	
	
	
	

	
	
	
	

}
