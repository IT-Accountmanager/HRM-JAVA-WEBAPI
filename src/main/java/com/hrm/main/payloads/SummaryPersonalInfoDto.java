package com.hrm.main.payloads;

import java.time.LocalDate;

import com.hrm.main.models.Helper.EnumCollection.BloodGroup;

public class SummaryPersonalInfoDto {

	private LocalDate dateOfBirth;
	private String gender;
	private BloodGroup bloodGroup;
	private String maritalStatus;

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public BloodGroup getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(BloodGroup bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

}
