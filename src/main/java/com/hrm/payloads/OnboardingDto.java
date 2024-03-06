package com.hrm.payloads;

import java.time.LocalDate;

import com.hrm.Helper.EnumCollection.CandidatesStatus;
import com.hrm.Helper.EnumCollection.WorkLocation;

public class OnboardingDto {
	private String jobTitleDesignation;
	private long candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private float serviceCommitment;
	private long serviceBreakAmount;
	private float ctc;
	private CandidatesStatus candidatesStatus;
	private LocalDate dateOfJoining;
	private WorkLocation workLocation;

	public String getJobTitleDesignation() {
		return jobTitleDesignation;
	}

	public void setJobTitleDesignation(String jobTitleDesignation) {
		this.jobTitleDesignation = jobTitleDesignation;
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

	public float getServiceCommitment() {
		return serviceCommitment;
	}

	public void setServiceCommitment(float serviceCommitment) {
		this.serviceCommitment = serviceCommitment;
	}

	public long getServiceBreakAmount() {
		return serviceBreakAmount;
	}

	public void setServiceBreakAmount(long serviceBreakAmount) {
		this.serviceBreakAmount = serviceBreakAmount;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public CandidatesStatus getCandidatesStatus() {
		return candidatesStatus;
	}

	public void setCandidatesStatus(CandidatesStatus candidatesStatus) {
		this.candidatesStatus = candidatesStatus;
	}

	public LocalDate getDateOfJoining() {
		return dateOfJoining;
	}

	public void setDateOfJoining(LocalDate dateOfJoining) {
		this.dateOfJoining = dateOfJoining;
	}

	public WorkLocation getWorkLocation() {
		return workLocation;
	}

	public void setWorkLocation(WorkLocation workLocation) {
		this.workLocation = workLocation;
	}

}
