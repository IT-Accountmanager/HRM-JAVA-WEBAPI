package com.hrm.main.models;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class PersonalDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pdid_seq")
	@SequenceGenerator(name = "pdid_seq", initialValue = 1, allocationSize = 1, sequenceName = "pdid_seq")
	private int id;
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

	@OneToOne(mappedBy = "permanent", cascade = CascadeType.ALL)
	private PermanentAddress perAddress;

	@OneToOne(mappedBy = "current", cascade = CascadeType.ALL)
	private PresentAddress preAddress;

	public PermanentAddress getPerAddress() {
		return perAddress;
	}

	public void setPerAddress(PermanentAddress perAddress) {
		this.perAddress = perAddress;
	}

	public PresentAddress getPreAddress() {
		return preAddress;
	}

	public void setPreAddress(PresentAddress preAddress) {
		this.preAddress = preAddress;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
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
