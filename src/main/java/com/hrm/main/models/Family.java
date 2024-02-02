package com.hrm.main.models;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;
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
	private String address;
	private String pincode;

	@Column(name = "candidate_id")
	private long candidateId;
	private DetailsSubmissionStatus familySubmissionStatus = DetailsSubmissionStatus.Pending;

	private ApprovalStatus hrExecutiveApprovalStatus = ApprovalStatus.Pending;

	private String hrExecutiveRemark;

	private ApprovalStatus hrManagerApprovalStatus = ApprovalStatus.Pending;

	private String hrManagerRemark;

	/*
	 * @ManyToOne
	 * 
	 * @JoinColumn(name = "profile_id") private Profile profile;
	 */

	public Family() {
		super();
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public long getCandidateId() {
		return candidateId;
	}

	public void setCandidateId(long candidateId) {
		this.candidateId = candidateId;
	}

	public DetailsSubmissionStatus getFamilySubmissionStatus() {
		return familySubmissionStatus;
	}

	public void setFamilySubmissionStatus(DetailsSubmissionStatus familySubmissionStatus) {
		this.familySubmissionStatus = familySubmissionStatus;
	}

	public ApprovalStatus getHrExecutiveApprovalStatus() {
		return hrExecutiveApprovalStatus;
	}

	public void setHrExecutiveApprovalStatus(ApprovalStatus hrExecutiveApprovalStatus) {
		this.hrExecutiveApprovalStatus = hrExecutiveApprovalStatus;
	}

	public String getHrExecutiveRemark() {
		return hrExecutiveRemark;
	}

	public void setHrExecutiveRemark(String hrExecutiveRemark) {
		this.hrExecutiveRemark = hrExecutiveRemark;
	}

	public ApprovalStatus getHrManagerApprovalStatus() {
		return hrManagerApprovalStatus;
	}

	public void setHrManagerApprovalStatus(ApprovalStatus hrManagerApprovalStatus) {
		this.hrManagerApprovalStatus = hrManagerApprovalStatus;
	}

	public String getHrManagerRemark() {
		return hrManagerRemark;
	}

	public void setHrManagerRemark(String hrManagerRemark) {
		this.hrManagerRemark = hrManagerRemark;
	}

	/*
	 * public Profile getProfile() { return profile; }
	 * 
	 * public void setProfile(Profile profile) { this.profile = profile; }
	 */

}
