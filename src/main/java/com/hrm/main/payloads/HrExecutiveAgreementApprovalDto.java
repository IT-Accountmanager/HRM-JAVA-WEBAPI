package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;

public class HrExecutiveAgreementApprovalDto {
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;

	public HrExecutiveAgreementApprovalDto() {
		super();
	}

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
