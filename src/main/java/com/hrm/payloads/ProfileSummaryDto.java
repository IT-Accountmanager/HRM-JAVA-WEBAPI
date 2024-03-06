package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;

public class ProfileSummaryDto {

	private DetailsSubmissionStatus personalSubmissionStatus;
	private DetailsSubmissionStatus familySubmissionStatus;
	private DetailsSubmissionStatus educationSubmissionStatus;
	private DetailsSubmissionStatus workSubmissionStatus;

	public DetailsSubmissionStatus getPersonalSubmissionStatus() {
		return personalSubmissionStatus;
	}

	public void setPersonalSubmissionStatus(DetailsSubmissionStatus personalSubmissionStatus) {
		this.personalSubmissionStatus = personalSubmissionStatus;
	}

	public DetailsSubmissionStatus getFamilySubmissionStatus() {
		return familySubmissionStatus;
	}

	public void setFamilySubmissionStatus(DetailsSubmissionStatus familySubmissionStatus) {
		this.familySubmissionStatus = familySubmissionStatus;
	}

	public DetailsSubmissionStatus getEducationSubmissionStatus() {
		return educationSubmissionStatus;
	}

	public void setEducationSubmissionStatus(DetailsSubmissionStatus educationSubmissionStatus) {
		this.educationSubmissionStatus = educationSubmissionStatus;
	}

	public DetailsSubmissionStatus getWorkSubmissionStatus() {
		return workSubmissionStatus;
	}

	public void setWorkSubmissionStatus(DetailsSubmissionStatus workSubmissionStatus) {
		this.workSubmissionStatus = workSubmissionStatus;
	}

}
