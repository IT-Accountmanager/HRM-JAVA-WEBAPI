package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.DetailsSubmissionStatus;

public class EducationStatusResponse {

	private DetailsSubmissionStatus status;

	public EducationStatusResponse(DetailsSubmissionStatus status) {
		super();
		this.status = status;
	}

	public DetailsSubmissionStatus getStatus() {
		return status;
	}

	public void setStatus(DetailsSubmissionStatus status) {
		this.status = status;
	}

}
