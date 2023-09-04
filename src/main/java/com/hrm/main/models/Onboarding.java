package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Onboarding {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Onboarding_seq")
	@SequenceGenerator(name = "Onboarding_seq", initialValue = 1, allocationSize = 1, sequenceName = "Onboarding_seq")
	private int srNo;
	private String jobTitle;
	private int candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private int bondPeriod;
	private int bondBreakAmount;
	private long ctc;
	private String status;

	public int getSrNo() {
		return srNo;
	}

	public void setSrNo(int srNo) {
		this.srNo = srNo;
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public int getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(int candidateId) {
		this.candidateId = candidateId;
	}

	public String getCandidateName() {
		return candidateName;
	}

	public void setCandidateName(String candidateName) {
		this.candidateName = candidateName;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public int getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(int bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public int getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(int bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public long getCtc() {
		return ctc;
	}

	public void setCtc(long ctc) {
		this.ctc = ctc;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Onboarding(int srNo, String jobTitle, int candidateId, String candidateName, long contactNumber,
			String emailId, int bondPeriod, int bondBreakAmount, long ctc, String status) {
		super();
		this.srNo = srNo;
		this.jobTitle = jobTitle;
		this.candidateId = candidateId;
		this.candidateName = candidateName;
		this.contactNumber = contactNumber;
		this.emailId = emailId;
		this.bondPeriod = bondPeriod;
		this.bondBreakAmount = bondBreakAmount;
		this.ctc = ctc;
		this.status = status;
	}

	public Onboarding() {
		super();
		// TODO Auto-generated constructor stub
	}

}
