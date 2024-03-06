package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.ApprovalStatus;

public class HrWorkApprovalDto {
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
