package com.hrm.main.models.Helper;

public class EnumCollection {

	public enum DetailsSubmissionStatus {
		Pending, Submitted
	}

	public enum CandidatesStatus {
		Pending, InReview, Approved, Rejected, HrExecutiveRejected, HRManagerRejected
	}

	public enum EmployeeStatus {
		Active, Inactive, Terminated, Absconded, Not_Reporting, Bench, Long_Leave, contractual, Probation_Period,
		Training_Period, Notice_Period
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
		MES(Department.DFS, Department.IAS, Department.EDS, Department.BIM),
		OD(Department.TAG, Department.HR, Department.FINANCE, Department.SALES, Department.SYSTEM_ADMIN),
		RD(Department.IT, Department.DIGITAL_MARKETING, Department.DEVELOPMENT);

		private final Department[] subdepartments;

		Departments(Department... subdepartments) {
			this.subdepartments = subdepartments;
		}

		public Department[] getSubdepartments() {
			return subdepartments;
		}

		public enum Department {
			DFS, IAS, EDS, BIM, TAG, HR, FINANCE, SALES, SYSTEM_ADMIN, IT, DIGITAL_MARKETING, DEVELOPMENT
		}
	}

	public enum ResignationStatus {
		SUBMITTED, APPROVED, REJECTED, PENDING_APPROVAL, WITHDRAWN
	}

	public enum Designation {
		JUNIOR_ENGINEER, JUNIOR_EXECUTIVE, ASSOCIATE_ENGINEER, ASSOCIATE_EXECUTIVE, SENIOR_ENGINEER, SENIOR_EXECUTIVE,
		TEAM_LEAD, PROJECT_MANAGER, PROGRAM_MANAGER, CXO
	}

	public enum BloodGroup {
		A_POSITIVE, A_NEGATIVE, B_POSITIVE, B_NEGATIVE, AB_POSITIVE, AB_NEGATIVE, O_POSITIVE, O_NEGATIVE
	}

	public enum SmsStatus {
		DELIVERED, FAILED
	}

	public enum AppraisalQuater {
		FY_21_22_Q1, FY_21_22_Q2, FY_21_22_Q3, FY_21_22_Q4, FY_22_23_Q1, FY_22_23_Q2, FY_22_23_Q3, FY_22_23_Q4,
		FY_23_24_Q1, FY_23_24_Q2, FY_23_24_Q3, FY_23_24_Q4, FY_24_25_Q1, FY_24_25_Q2, FY_24_25_Q3, FY_24_25_Q4,
		FY_25_26_Q1, FY_25_26_Q2, FY_25_26_Q3, FY_25_26_Q4,

	}

}
