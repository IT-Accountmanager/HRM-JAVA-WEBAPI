package com.hrm.main.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "hr_executive")
public class OnboardingHRExecutive {

	@Id
	/*
	 * @GeneratedValue(strategy = GenerationType.SEQUENCE, generator =
	 * "HrExecutive_Id_Sequence")
	 * 
	 * @SequenceGenerator(name = "HrExecutive_Id_Sequence", initialValue = 1,
	 * allocationSize = 1, sequenceName = "HrExecutive_Id_Sequence")
	 */
	private int id;
	private String jobTitle;
	private int candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private int bondPeriod;
	private int bondBreakAmount;
	private long ctc;

	public enum Status {
		Review, Pending, Approved, Rejected
	};

	private Status status;

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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public OnboardingHRExecutive(int id, String jobTitle, int candidateId, String candidateName, long contactNumber,
			String emailId, int bondPeriod, int bondBreakAmount, long ctc, Status status) {
		super();
		this.id = id;
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
	
	

}
