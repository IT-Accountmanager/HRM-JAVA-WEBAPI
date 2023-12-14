package com.hrm.main.models.Helper;

public class EnumCollection {

	public enum DetailsSubmissionStatus {
		Pending, Submitted
	}

	public enum CandidatesStatus {
		Pending, In_Review, Approved, Rejected
	}

	public enum EmployeeStatus {
		Active, Inactive
	}

	public enum ApprovalStatus {
		Pending, Approve, Reject
	}

	public enum HrSubmission {
		Reject, Submit
	}

	public enum AttendanceStatus {
		Present, Absent, WFH
	}

	public enum Departments {
		MES, OD, ITE
	}

	public enum Sub_Department {
		Management, DigitalFactorySolution, EngineeringDesignSolution, IndustrialAutomationSolution,
		BuildingInformationModeling, FrontEnd, BackEnd, Testing, HumanResource, Finance, Sales, SystemAdmin,
		Development, Recruitment, DigitalMarketing
	}

	public enum ResignationStatus {
		SUBMITTED, APPROVED, REJECTED, PENDING_APPROVAL, WITHDRAWN
	}
}
