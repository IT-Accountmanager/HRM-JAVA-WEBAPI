package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;

public class FamilyStatusResponse {
	private DetailsSubmissionStatus status;

	public FamilyStatusResponse(DetailsSubmissionStatus status) {
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
