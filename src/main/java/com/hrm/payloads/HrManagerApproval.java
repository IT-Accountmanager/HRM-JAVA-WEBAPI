package com.hrm.payloads;

import com.hrm.helper.EnumCollection.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class HrManagerApproval {
	private ApprovalStatus hrManagerApprovalStatus = ApprovalStatus.Pending;
	private String hrManagerRemark;

}
