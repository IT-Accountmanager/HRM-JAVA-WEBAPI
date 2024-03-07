package com.hrm.payloads;

import com.hrm.helper.EnumCollection.HrSubmission;

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
