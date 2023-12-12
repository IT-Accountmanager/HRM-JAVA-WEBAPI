package com.hrm.main.models;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "hr_executive")
public class HRExecutive {

	@Id

	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "HrExecutive_Id_Sequence")

	@SequenceGenerator(name = "HrExecutive_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "HrExecutive_Id_Sequence")

	private int id;
	private String jobTitle;
	private long candidateId;
	private String candidateName;
	private long contactNumber;
	private String emailId;
	private float serviceCommitment;
	private long serviceBreakAmount;
	private float ctc;

	private CandidatesStatus status;

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

	public CandidatesStatus getStatus() {
		return status;
	}

	public void setStatus(CandidatesStatus status) {
		this.status = status;
	}

	public HRExecutive() {
	}

}
