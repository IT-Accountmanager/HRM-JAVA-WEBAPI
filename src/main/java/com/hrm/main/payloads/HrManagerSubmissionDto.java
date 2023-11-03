package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.HrSubmission;

public class HrManagerSubmissionDto {

	private HrSubmission hrManagerSubmission;

	public HrManagerSubmissionDto() {
		super();
	}

	public HrSubmission getHrManagerSubmission() {
		return hrManagerSubmission;
	}

	public void setHrManagerSubmission(HrSubmission hrManagerSubmission) {
		this.hrManagerSubmission = hrManagerSubmission;
	}

}
