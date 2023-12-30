package com.hrm.main.models.Helper;

public class EnumCollection {

	public enum DetailsSubmissionStatus {
		Pending, Submitted
	}

	public enum CandidatesStatus {
		Pending, InReview, Approved, Rejected, HrExecutiveRejected, HRManagerRejected
	}

	public enum EmployeeStatus {
		Active, Inactive, Absconded, NoticePeriod, ProbationPeriod, TrainingPeriod
	}

	public enum ApprovalStatus {
		Pending, Approved, Rejected
	}

	public enum HrSubmission {
		Pending, Submit, Reject
	}

	public enum AttendanceStatus {
		Present, Anomaly, WeekOff, Holiday, Leave
	}

	public enum Departments {
		MES, OD, ITE
	}

	public enum Sub_Department {
		Management, DigitalFactorySolution, EngineeringDesignSolution, IndustrialAutomationSolution,
		BuildingInformationModeling, FrontEnd, BackEnd, Testing, HumanResource, Finance, Sales, SystemAdmin,
		Development, Recruitment, DigitalMarketing, Tag, Dm
	}

	public enum ResignationStatus {
		SUBMITTED, APPROVED, REJECTED, PENDING_APPROVAL, WITHDRAWN
	}
}
