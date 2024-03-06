package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;

public class HrExecutiveBgvSubmissionDto {

	private DetailsSubmissionStatus backgroundVerificationSubmissionStatus;

	public DetailsSubmissionStatus getBackgroundVerificationSubmissionStatus() {
		return backgroundVerificationSubmissionStatus;
	}

	public void setBackgroundVerificationSubmissionStatus(DetailsSubmissionStatus backgroundVerificationSubmissionStatus) {
		this.backgroundVerificationSubmissionStatus = backgroundVerificationSubmissionStatus;
	}
	

}
