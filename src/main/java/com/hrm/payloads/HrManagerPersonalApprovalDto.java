package com.hrm.payloads;

import com.hrm.Helper.EnumCollection.ApprovalStatus;

public class HrManagerPersonalApprovalDto {

	private ApprovalStatus hrManagerApprovalStatus;
	private String hrManagerRemark;

	public HrManagerPersonalApprovalDto() {
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
