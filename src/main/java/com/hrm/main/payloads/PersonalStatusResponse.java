package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

public class PersonalStatusResponse {

	private DetailsSubmissionStatus status;

	public PersonalStatusResponse(DetailsSubmissionStatus status) {
		this.status = status;
	}

	public DetailsSubmissionStatus getStatus() {
		return status;
	}

	public void setStatus(DetailsSubmissionStatus status) {
		this.status = status;
	}
}