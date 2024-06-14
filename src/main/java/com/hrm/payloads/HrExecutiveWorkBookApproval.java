package com.hrm.payloads;

import com.hrm.helper.EnumCollection.ApprovalStatus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
public class HrExecutiveWorkBookApproval {
	private Long candidateId;
	private ApprovalStatus hrExecutiveApprovalStatus;
	private String hrExecutiveRemark;

}
