package com.hrm.payloads;

import com.hrm.helper.EnumCollection.ApprovalStatus;

public class HrManagerFamilyApprovalDto {
	private ApprovalStatus hrManagerApprovalStatus;
	private String hrManagerRemark;

	public HrManagerFamilyApprovalDto() {
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
