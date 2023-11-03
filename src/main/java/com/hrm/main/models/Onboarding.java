
package com.hrm.main.models;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.HrSubmission;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "onboarding", uniqueConstraints = @UniqueConstraint(columnNames = "candidate_id"))
public class Onboarding {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Onboarding_seq")
	@SequenceGenerator(name = "Onboarding_seq", initialValue = 1, allocationSize = 1, sequenceName = "Onboarding_seq")
	private int srNo;
	@Column(name = "job_title")
	private String jobTitle;

	@Column(name = "candidate_id")
	private long candidateId;

	@Column(name = "candidate_name")
	private String candidateName;

	@Column(name = "contact_number")
	private long contactNumber;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "bond_period")
	private float bondPeriod;

	@Column(name = "bond_break_amount")
	private int bondBreakAmount;

	@Column(name = "ctc")
	private float ctc;

	private CandidatesStatus candidatesStatus;

	private HrSubmission hrExecutiveSubmission;
	private HrSubmission hrManagerSubmission;

	// private Profile profile;

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

	public CandidatesStatus getCandidatesStatus() {
		return candidatesStatus;
	}

	public void setCandidatesStatus(CandidatesStatus candidatesStatus) {
		this.candidatesStatus = candidatesStatus;
	}

	public HrSubmission getHrExecutiveSubmission() {
		return hrExecutiveSubmission;
	}

	public void setHrExecutiveSubmission(HrSubmission hrExecutiveSubmission) {
		this.hrExecutiveSubmission = hrExecutiveSubmission;
	}

	public HrSubmission getHrManagerSubmission() {
		return hrManagerSubmission;
	}

	public void setHrManagerSubmission(HrSubmission hrManagerSubmission) {
		this.hrManagerSubmission = hrManagerSubmission;
	}

}
