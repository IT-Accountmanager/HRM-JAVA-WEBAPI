package com.hrm.main.models.Helper;

public class EnumCollection {

	public enum DetailsSubmissionStatus {
		Pending, Submitted
	}

	public enum CandidatesStatus {
		Pending, InReview, Approved, Rejected, HrExecutiveRejected, HRManagerRejected
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
		MECHANICAL_ENGINEERING_SERVICES(Department.DIGITAL_FACTORY_SOLUTION, Department.INDUSTRIAL_AUTOMATION_SOLUTION,
				Department.ENGINEERING_DESIGN_SOLUTION, Department.BUILDING_INFORMATION_MODELING),
		OPERATIONAL_DEPARTMENT(Department.TALENT_ACQUISITION, Department.HUMAN_RESOURCE, Department.FINANCE,
				Department.SALES, Department.SYSTEM_ADMIN),
		RESEARCH_AND_DEVELOPMENT(Department.INFORMATION_TECHNOLOGY, Department.DIGITAL_MARKETING,
				Department.DEVELOPMENT);

		private final Department[] subdepartments;

		Departments(Department... subdepartments) {
			this.subdepartments = subdepartments;
		}

		public Department[] getSubdepartments() {
			return subdepartments;
		}

		public enum Department {
			DIGITAL_FACTORY_SOLUTION, INDUSTRIAL_AUTOMATION_SOLUTION, ENGINEERING_DESIGN_SOLUTION,
			BUILDING_INFORMATION_MODELING, TALENT_ACQUISITION, HUMAN_RESOURCE, FINANCE, SALES, SYSTEM_ADMIN,
			INFORMATION_TECHNOLOGY, DIGITAL_MARKETING, DEVELOPMENT
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

	public enum HouseType {
		OWNED_BY_SELF_OR_SPOUSE, OWNED_BY_PARENT_OR_SIBLING, RENTED_WITH_FAMILY, RENTED_WITH_FRIENDS,
		RENTED_STAYING_ALONE, PAYING_GUEST, HOSTEL, COMPANY_PROVIDED, OTHERS
	}

	public enum ProbationPeriod {
		_1_Month, _2_Month, _3_Month, _4_Month, _5_Month, _6_Month
	}

	public enum WorkLocation {
		HYDERABAD, CHENNAI, BANGALORE, PUNE
	}

	public enum ManagerType {
		PRIMARY, SECONDARY
	}

	public enum Gender {
		MALE("Male"), FEMALE("Female"), OTHER("Other");

		private final String gender;

		Gender(String gender) {
			this.gender = gender;
		}
	}

	public enum MaritalStatus {
		SINGLE, MARRIED, DIVORCED, WIDOWED,
	}

	public enum CourseType {
		REGULAR, DISTANCE
	}

	public enum ShiftRule {
		RuleSettings_1, RuleSettings_2, RuleSettings_3
	}

	public enum WeekRule {
		RuleSettings_1, RuleSettings_2, RuleSettings_3
	}

	public enum Resignation {
		Rejected, Approved
	}

	public enum NoticePeriod {
		_30_Days, _60_Days, _90_Days, _120_Days
	}

	public enum EmployeeStatus {
		Active, Inactive
	}

	public enum EmployeeCategory {
		Working, WalkOut, Terminated, Absconded, NotReporting, Bench, LongLeave, Contractual, ProbationPeriod,
		TrainingPeriod, NoticePeriod
	}

	public enum LeaveType {
		EarnedLeave, CompensatoryLeave, LossOfPay
	}

	public enum Half {
		FirstHalf, SecondHalf
	}

}
