package com.hrm.main.models;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;

@Entity

public class HRManager {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Onboarding_Id_Sequence")
	@SequenceGenerator(name = "Onboarding_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Onboarding_Id_Sequence")

	private int id;
	private String jobTitle;
	private long candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private float bondPeriod;
	private int bondBreakAmount;
	private float ctc;

	private CandidatesStatus status;

	public HRManager() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
