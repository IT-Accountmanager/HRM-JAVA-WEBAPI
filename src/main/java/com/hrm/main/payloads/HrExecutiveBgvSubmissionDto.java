package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

public class HrExecutiveBgvSubmissionDto {

	private DetailsSubmissionStatus backgroundVerificationSubmissionStatus;

	public DetailsSubmissionStatus getBackgroundVerificationSubmissionStatus() {
		return backgroundVerificationSubmissionStatus;
	}

	public void setBackgroundVerificationSubmissionStatus(DetailsSubmissionStatus backgroundVerificationSubmissionStatus) {
		this.backgroundVerificationSubmissionStatus = backgroundVerificationSubmissionStatus;
	}
	

}
