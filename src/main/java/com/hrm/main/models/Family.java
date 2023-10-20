package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Onboarding.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "family")
public class Family {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Family_Id_Sequence")
	@SequenceGenerator(name = "Family_Id_Sequence", initialValue = 1, allocationSize = 1, sequenceName = "Family_Id_Sequence")
	@Column(name = "family_id")
	private int familyId;
	private String name;
	private String relationship;
	private int age;
	private LocalDate dateOfBirth;
	private long phoneNumber;
	private String emailId;
	private String address;

	@Column(name = "candidate_id")
	private String candidateId;
	private DetailsSubmissionStatus familySubmissionStatus;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "profile_id") private Profile profile;
	 */

	public int getFamilyId() {
		return familyId;
	}

	public void setFamilyId(int familyId) {
		this.familyId = familyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(String candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getFamilySubmissionStatus() {
		return familySubmissionStatus;
	}

	public void setFamilySubmissionStatus(DetailsSubmissionStatus familySubmissionStatus) {
		this.familySubmissionStatus = familySubmissionStatus;
	}

	/*
	 * public Profile getProfile() { return profile; }
	 * 
	 * public void setProfile(Profile profile) { this.profile = profile; }
	 */

}
