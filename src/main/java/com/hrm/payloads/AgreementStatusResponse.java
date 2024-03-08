package com.hrm.payloads;

import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;

public class AgreementStatusResponse {
	private DetailsSubmissionStatus status;

	public AgreementStatusResponse(DetailsSubmissionStatus status) {
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
