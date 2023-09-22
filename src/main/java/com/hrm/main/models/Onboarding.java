
package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "onboarding", uniqueConstraints = @UniqueConstraint(columnNames = "candidateId"))
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
	private float bondPeriod;
	private int bondBreakAmount;
	private float ctc;

	public enum Status {
		Review, Pending, Approved, Rejected
	};

	private Status status;

	//private Profile profile;

	/*
	 * @OneToOne(cascade = CascadeType.ALL)
	 * 
	 * @JoinTable(name = "o_p_id", joinColumns = {
	 * 
	 * @JoinColumn(name = "o_id", referencedColumnName = "srNo") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "p_id", referencedColumnName = "profileId") }) private
	 * Profile profile;
	 */

	/*
	 * public Profile getProfile() { return profile; }
	 * 
	 * public void setProfile(Profile profile) { this.profile = profile; }
	 */

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

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

	public float getBondPeriod() {
		return bondPeriod;
	}

	public void setBondPeriod(float bondPeriod) {
		this.bondPeriod = bondPeriod;
	}

	public float getCtc() {
		return ctc;
	}

	public void setCtc(float ctc) {
		this.ctc = ctc;
	}

	public int getBondBreakAmount() {
		return bondBreakAmount;
	}

	public void setBondBreakAmount(int bondBreakAmount) {
		this.bondBreakAmount = bondBreakAmount;
	}

}
