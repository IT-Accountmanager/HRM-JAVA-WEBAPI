package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.HrExecutiveSubmission;

public class HrExecutiveSubmissionDto {

	private HrExecutiveSubmission hrExecutiveSubmission;

	public HrExecutiveSubmission getHrExecutiveSubmission() {
		return hrExecutiveSubmission;
	}

	public void setHrExecutiveSubmission(HrExecutiveSubmission hrExecutiveSubmission) {
		this.hrExecutiveSubmission = hrExecutiveSubmission;
	}

	public HrExecutiveSubmissionDto(HrExecutiveSubmission hrExecutiveSubmission) {
		super();
		this.hrExecutiveSubmission = hrExecutiveSubmission;
	}

}
