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

}
