package com.hrm.main.payloads;

import com.hrm.main.models.Helper.EnumCollection.ApprovalStatus;

public class HrExecutiveFamilyApprovalDto {

	private ApprovalStatus status;
	private String remark;

	public ApprovalStatus getStatus() {
		return status;
	}

	public void setStatus(ApprovalStatus status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
