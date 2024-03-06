package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.DetailsSubmissionStatus;

public class WorkStatusResponse {

	private DetailsSubmissionStatus status;

	public WorkStatusResponse(DetailsSubmissionStatus status) {
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
