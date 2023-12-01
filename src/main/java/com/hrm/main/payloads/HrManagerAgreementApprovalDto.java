package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;

public class HrManagerAgreementApprovalDto {
	private ApprovalStatus hrManagerApprovalStatus;
	private String hrManagerRemark;

	public HrManagerAgreementApprovalDto() {
		super();
	}

	public ApprovalStatus getHrManagerApprovalStatus() {
		return hrManagerApprovalStatus;
	}

	public void setHrManagerApprovalStatus(ApprovalStatus hrManagerApprovalStatus) {
		this.hrManagerApprovalStatus = hrManagerApprovalStatus;
	}

	public String getHrManagerRemark() {
		return hrManagerRemark;
	}

	public void setHrManagerRemark(String hrManagerRemark) {
		this.hrManagerRemark = hrManagerRemark;
	}

}
