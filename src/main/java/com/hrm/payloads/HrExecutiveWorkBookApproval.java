package com.hrm.payloads;

import com.hrm.helper.EnumCollection.ApprovalStatus;

import lombok.Data;

@Data
public class HrExecutiveWorkBookApproval {
	private Long candidateId;
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;

}
