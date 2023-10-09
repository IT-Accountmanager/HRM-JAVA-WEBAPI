package com.hrm.main.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Transient;

@Entity
public class PersonalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdid_seq")
	@SequenceGenerator(name = "pdid_seq", initialValue = 1, allocationSize = 1, sequenceName = "pdid_seq")
	private int pdid;
	@Column(name = "first_name")
	private String firstName;

	@Column(name = "middle_name")
	private String middleName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "date_of_birth")
	private String dateOfBirth;

	@Column(name = "gender")
	private String gender;

	@Column(name = "blood_group")
	private String bloodGroup;

	@Column(name = "marital_status")
	private String maritalStatus;

	@Column(name = "official_mail_id")
	private String officialMailId;

	@Column(name = "personal_mail_id")
	private String personalMailId;

	@Column(name = "phone_no")
	private Long phoneNo;

	@Column(name = "alternative_phone_no")
	private Long alternativePhoneNo;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] profilePhoto;
	@Transient
	public String base64Data;

	/*
	 * @OneToOne(mappedBy = "permanent", cascade = CascadeType.ALL) private
	 * PermanentAddress perAddress;
	 * 
	 * @OneToOne(mappedBy = "current", cascade = CascadeType.ALL) private
	 * PresentAddress preAddress;
	 */

	/*
	 * public PermanentAddress getPerAddress() { return perAddress; }
	 * 
	 * public void setPerAddress(PermanentAddress perAddress) { this.perAddress =
	 * perAddress; }
	 * 
	 * public PresentAddress getPreAddress() { return preAddress; }
	 * 
	 * public void setPreAddress(PresentAddress preAddress) { this.preAddress =
	 * preAddress; }
	 */

	public String getFirstName() {
		return firstName;
	}

	public int getPdid() {
		return pdid;
	}

	public void setPdid(int pdid) {
		this.pdid = pdid;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getOfficialMailId() {
		return officialMailId;
	}

	public void setOfficialMailId(String officialMailId) {
		this.officialMailId = officialMailId;
	}

	public String getPersonalMailId() {
		return personalMailId;
	}

	public void setPersonalMailId(String personalMailId) {
		this.personalMailId = personalMailId;
	}

	public Long getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(Long phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Long getAlternativePhoneNo() {
		return alternativePhoneNo;
	}

	public void setAlternativePhoneNo(Long alternativePhoneNo) {
		this.alternativePhoneNo = alternativePhoneNo;
	}

	public byte[] getProfilePhoto() {
		return profilePhoto;
	}

	public void setProfilePhoto(byte[] profilePhoto) {
		this.profilePhoto = profilePhoto;
	}

}
