package com.hrm.main.payloads;

public class SummaryContactInfoDto {

	private String personalMailId;
	private String officialMailId;
	private long contactNumber;
	private long alternativeNumber;

	public String getPersonalMailId() {
		return personalMailId;
	}

	public void setPersonalMailId(String personalMailId) {
		this.personalMailId = personalMailId;
	}

	public String getOfficialMailId() {
		return officialMailId;
	}

	public void setOfficialMailId(String officialMailId) {
		this.officialMailId = officialMailId;
	}

	public long getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(long contactNumber) {
		this.contactNumber = contactNumber;
	}

	public long getAlternativeNumber() {
		return alternativeNumber;
	}

	public void setAlternativeNumber(long alternativeNumber) {
		this.alternativeNumber = alternativeNumber;
	}

}
