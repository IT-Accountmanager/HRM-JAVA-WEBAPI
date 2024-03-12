package com.hrm.models;

import java.time.LocalDate;

import com.hrm.helper.EnumCollection.ApprovalStatus;
import com.hrm.helper.EnumCollection.LeaveType;

public class LeaveManagement {

	private String employeeId;
	private LocalDate leaveStartingDate;
	private LocalDate leaveEndingDate;
	private int noOfDays;
	private LocalDate dateAppliedOn;
	private LocalDate dateOfApproved;
	private String comments;
	private LeaveType leaveType;
	private ApprovalStatus status;

}
