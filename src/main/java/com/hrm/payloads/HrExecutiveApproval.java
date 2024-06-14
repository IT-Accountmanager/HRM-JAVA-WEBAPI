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
public class HrExecutiveApproval {
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;

}
