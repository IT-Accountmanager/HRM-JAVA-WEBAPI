package com.hrm.payloads;

import com.hrm.helper.EnumCollection.ApprovalStatus;

public class HrEducationApprovalDto {
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;

	public ApprovalStatus getHrExecutiveApprovalStatus() {
		return hrExecutiveApprovalStatus;
	}

	public void setHrExecutiveApprovalStatus(ApprovalStatus hrExecutiveApprovalStatus) {
		this.hrExecutiveApprovalStatus = hrExecutiveApprovalStatus;
	}

	public String getHrExecutiveRemark() {
		return hrExecutiveRemark;
	}

	public void setHrExecutiveRemark(String hrExecutiveRemark) {
		this.hrExecutiveRemark = hrExecutiveRemark;
	}
}