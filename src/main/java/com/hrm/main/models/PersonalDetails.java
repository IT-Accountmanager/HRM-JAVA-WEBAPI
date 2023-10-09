package com.hrm.main.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "personal_details")
public class PersonalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdid_seq")
	@SequenceGenerator(name = "pdid_seq", initialValue = 1, allocationSize = 1, sequenceName = "pdid_seq")

	private int pdid;
	private String firstName;
	private String middleName;
	private String lastName;
	private String dateOfBirth;
	private String gender;
	private String bloodGroup;
	private String maritalStatus;
	private String officialMailId;
	private String personalMailId;
	private Long phoneNo;
	private Long alternativePhoneNo;

	@Column(columnDefinition = "LONGBLOB")
	private byte[] imageData;

	public PersonalDetails(Long id, String firstName, String middleName, String lastName, String dateOfBirth,
			String gender, String bloodGroup, String maritalStatus, String officialMailId, byte[] imageData,
			String personalMailId, Long phoneNo, Long alternativePhoneNo, int pdid) {
		super();
		this.pdid = pdid;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.bloodGroup = bloodGroup;
		this.maritalStatus = maritalStatus;
		this.officialMailId = officialMailId;
		this.imageData = imageData;
		this.personalMailId = personalMailId;
		this.phoneNo = phoneNo;
		this.alternativePhoneNo = alternativePhoneNo;
	}

	public byte[] getImageData() {
		return imageData;
	}

	public void setImageData(byte[] imageData) {
		this.imageData = imageData;
	}

	@Lob

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

}
