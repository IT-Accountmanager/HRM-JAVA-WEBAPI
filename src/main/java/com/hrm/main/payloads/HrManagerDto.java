package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;

public class HrManagerDto {
	private String jobTitle;
	private long candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private float bondPeriod;
	private int bondBreakAmount;
	private float ctc;
	private CandidatesStatus status;

	public HrManagerDto() {
		super();
	}

	public String getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
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

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public int getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(int bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public CandidatesStatus getStatus() {
		return status;
	}

	public void setStatus(CandidatesStatus status) {
		this.status = status;
	}

}
