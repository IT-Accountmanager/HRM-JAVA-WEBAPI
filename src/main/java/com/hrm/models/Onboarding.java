
package com.hrm.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.HrSubmission;
import com.hrm.helper.EnumCollection.WorkLocation;

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
	@Column(name = "candidate_id")
	private Long candidateId;

	@Column(name = "job_title_designation")
	private Designation jobTitleDesignation;

	@Column(name = "candidate_name")
	private String candidateName;

	@Column(name = "contact_number")
	private long contactNumber;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "service_commitment")
	private float serviceCommitment;

	@Column(name = "service_break_amount")
	private long serviceBreakAmount;

	@Column(name = "ctc")
	private long ctc;
	private String experience;

	private CandidatesStatus candidatesStatus;

	private HrSubmission hrExecutiveSubmission;

	private HrSubmission hrManagerSubmission;

	private LocalDate dateOfJoining;
	private WorkLocation workLocation;
	private String formattedDate;
	private static final long OTP_VALID_DURATION = 5 * 60 * 1000; // 5 minutes

	private String password;

	@Column(name = "one_time_password")
	private String oneTimePassword;

	@Column(name = "otp_requested_time")
	private LocalDateTime otpRequestedTime;

	public String getFormattedDate() {
		return formattedDate;
	}

	public void setFormattedDate(String formattedDate) {
		this.formattedDate = formattedDate;
	}

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

	public String getFormattedDateOfJoining() {
		if (dateOfJoining != null) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
			return dateOfJoining.format(formatter);
		}
		return null; // or return an empty string or handle as appropriate
	}

	public Designation getJobTitleDesignation() {
		return jobTitleDesignation;
	}

	public void setJobTitleDesignation(Designation jobTitleDesignation) {
		this.jobTitleDesignation = jobTitleDesignation;
	}

	public void setCandidateId(Long candidateId) {
		this.candidateId = candidateId;
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

	public long getCtc() {
		return ctc;
	}

	public void setCtc(long ctc) {
		this.ctc = ctc;
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

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public HrSubmission getHrManagerSubmission() {
		return hrManagerSubmission;
	}

	public void setHrManagerSubmission(HrSubmission hrManagerSubmission) {
		this.hrManagerSubmission = hrManagerSubmission;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getOneTimePassword() {
		return oneTimePassword;
	}

	public void setOneTimePassword(String oneTimePassword) {
		this.oneTimePassword = oneTimePassword;
	}

	public LocalDateTime getOtpRequestedTime() {
		return otpRequestedTime;
	}

	public void setOtpRequestedTime(LocalDateTime otpRequestedTime) {
		this.otpRequestedTime = otpRequestedTime;
	}

	public static long getOtpValidDuration() {
		return OTP_VALID_DURATION;
	}

	public boolean isOTPRequired() {
		if (this.getOneTimePassword() == null) {
			return false;
		}

		long currentTimeInMillis = System.currentTimeMillis();
		long otpRequestedTimeInMillis = otpRequestedTime.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();

		if (otpRequestedTimeInMillis + OTP_VALID_DURATION < currentTimeInMillis) {
			// OTP expires
			return false;
		}

		return true;
	}

}
