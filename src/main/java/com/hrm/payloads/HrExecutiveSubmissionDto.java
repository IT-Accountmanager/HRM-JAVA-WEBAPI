package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.HrSubmission;

public class HrExecutiveSubmissionDto {

	private HrSubmission hrExecutiveSubmission;

	public HrSubmission getHrExecutiveSubmission() {
		return hrExecutiveSubmission;
	}

	public void setHrExecutiveSubmission(HrSubmission hrExecutiveSubmission) {
		this.hrExecutiveSubmission = hrExecutiveSubmission;
	}

	public HrExecutiveSubmissionDto(HrSubmission hrExecutiveSubmission) {
		super();
		this.hrExecutiveSubmission = hrExecutiveSubmission;
	}

}
