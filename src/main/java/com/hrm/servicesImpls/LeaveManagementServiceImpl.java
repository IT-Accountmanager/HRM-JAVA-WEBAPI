package com.hrm.servicesImpls;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.exception.ServiceException;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Departments.Department;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.Employee;
import com.hrm.models.LeaveManagementTable;
import com.hrm.models.LeaveSummary;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.LeaveDetailsRequestDto;
import com.hrm.payloads.LeaveRequestDetailsDto;
import com.hrm.payloads.ManagerLeaveDetailsDto;
import com.hrm.payloads.ManagerLeaveEditDto;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.ILeaveSummaryRepository;
import com.hrm.repositories.LeaveManagementRepo;
import com.hrm.services.LeaveManagementService;

@Service

public class LeaveManagementServiceImpl implements LeaveManagementService {
	@Autowired
	LeaveManagementRepo leaveManagementRepo;

	@Autowired
	IEmployeeRepository employeeRepository;

	@Autowired
	ILeaveSummaryRepository leaveSummaryRepository;

	private static final Logger logger = LoggerFactory.getLogger(LeaveManagementServiceImpl.class);

	public double calculateAppliedDaysForLeave(LocalDate leaveStartDate, LocalDate leaveEndDate, Half half1,
			Half half2) {
		double appliedDaysForLeave = 0;

		long totalDays = ChronoUnit.DAYS.between(leaveStartDate, leaveEndDate);

		if (half1 == Half.First_Half && half2 == Half.First_Half) {
			appliedDaysForLeave += 0.5;
		}
		if (half1 == Half.First_Half && half2 == Half.Second_Half) {
			appliedDaysForLeave += 1;
		}
		if (half1 == Half.Second_Half && half2 == Half.First_Half) {
			appliedDaysForLeave += 0;
		}
		if (half1 == Half.Second_Half && half2 == Half.Second_Half) {
			appliedDaysForLeave += 0.5;
		}

		appliedDaysForLeave += totalDays;

		return appliedDaysForLeave;
	}

	@Override
	public String addLeave(ApplyLeaveDto applyLeaveDto) {
		Half half1 = applyLeaveDto.getHalf1();
		Half half2 = applyLeaveDto.getHalf2();
		LocalDate leaveStartDate = applyLeaveDto.getLeaveStartDate();
		LocalDate leaveEndDate = applyLeaveDto.getLeaveEndDate();
		String employeeId = applyLeaveDto.getEmployeeId();
		double appliedDaysForLeave = 0;
		appliedDaysForLeave = calculateAppliedDaysForLeave(leaveStartDate, leaveEndDate, half1, half2);

		try {
			if (applyLeaveDto == null || employeeId == null) {
				throw new IllegalArgumentException("Invalid input parameters");
			}

			LeaveManagementTable leaveManagementTable = new LeaveManagementTable();

			leaveManagementTable.setLeaveType(applyLeaveDto.getLeaveType());
			leaveManagementTable.setLeaveStartDate(leaveStartDate);
			leaveManagementTable.setLeaveEndDate(leaveEndDate);
			leaveManagementTable.setHalf1(half1);
			leaveManagementTable.setHalf2(half2);
			leaveManagementTable.setLeaveReason(applyLeaveDto.getLeaveReason());
			leaveManagementTable.setManagerId(applyLeaveDto.getManagerId());
			leaveManagementTable.setAppliedDaysForLeave(appliedDaysForLeave);
			leaveManagementTable.setEmployeeId(employeeId);

			leaveManagementRepo.save(leaveManagementTable);

			return "Leave Added Successfully for employee Id " + employeeId + ". Number of days: " + appliedDaysForLeave
					+ ". Start Month: ";
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("Failed to add leave for employee: {}", employeeId, e);

			return "Failed to add leave. Please check the input parameters and try again.";
		}

	}

	private List<ManagerLeaveDetailsDto> processLeaveDetails(List<LeaveManagementTable> allLeaveDetails,
			List<ManagerLeaveDetailsDto> leaveDetailsDtoList) {
		try {
			if (allLeaveDetails != null) {
				for (LeaveManagementTable leaveDetails : allLeaveDetails) {
					Employee subDepartmentAndName = this.employeeRepository
							.findSubDepartmentAndNameByEmployeeId(leaveDetails.getEmployeeId());

					ManagerLeaveDetailsDto dto = new ManagerLeaveDetailsDto();
					dto.setId(leaveDetails.getId());
					dto.setMonth(leaveDetails.getLeaveStartDate().getMonth());
					dto.setSubDepartment(subDepartmentAndName.getSubDepartment());
					dto.setEmployeeId(leaveDetails.getEmployeeId());
					dto.setEmployeeName(subDepartmentAndName.getName());
					dto.setLeaveType(leaveDetails.getLeaveType());
					dto.setStartDate(leaveDetails.getLeaveStartDate());
					dto.setEndDate(leaveDetails.getLeaveEndDate());
					dto.setAppliedDaysForLeave(leaveDetails.getAppliedDaysForLeave());
					dto.setApprovedDaysForLeave(leaveDetails.getApprovedDaysForLeave());
					dto.setRemarks(leaveDetails.getRemarks());

					leaveDetailsDtoList.add(dto);

				}

			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return leaveDetailsDtoList;
	}

	@Override
	public List<ManagerLeaveDetailsDto> getLeaveDetails(LeaveDetailsRequestDto leaveDetailsRequestDto) {

		// LocalDate year = leaveDetailsRequestDto.getYear();
		Year year = leaveDetailsRequestDto.getYear();
		Month month = leaveDetailsRequestDto.getMonth();
		String employeeId = leaveDetailsRequestDto.getEmployeeId();
		String managerId = leaveDetailsRequestDto.getManagerId();

		List<ManagerLeaveDetailsDto> leaveDetailsDtoList = new ArrayList<>();
		List<LeaveManagementTable> allLeaveDetails = new ArrayList<>();

		if (year == null) {
//			year = LocalDate.now();
			year = Year.now();
		}

		if (month != null) {
//			year = year.withMonth(month.getValue());
			year = Year.now();
		} else {
			month = (LocalDate.now()).getMonth();
		}

//		int _year = year.getYear();
//		int _month = year.getMonthValue();
		int _year = year.getValue();
		int _month = month.getValue();

//		if (employeeId != null && !employeeId.isEmpty()) {
//			allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId, employeeId,
//					_year + "-" + _month);
//		} else if (managerId != null && !managerId.isEmpty()) {
//			allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId, employeeId,
//					_year + "-" + _month);
//		} else {
//			throw new IllegalArgumentException("Both employeeId and managerId cannot be null");
//		}

		if (managerId != null && !managerId.isEmpty()) {
			if (employeeId != null && !employeeId.isEmpty()) {
				allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId,
						employeeId, _year + "-" + _month);
			} else {
				allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYear(managerId, _year + "-" + _month);
			}

		}
		if (employeeId != null && !employeeId.isEmpty()) {
			if (managerId != null && !managerId.isEmpty()) {
				allLeaveDetails = this.leaveManagementRepo.findByManagerIdAndYearOrEmployeeIdAndYear(managerId,
						employeeId, _year + "-" + _month);
			} else {
				allLeaveDetails = this.leaveManagementRepo.findByEmployeeIdAndYear(employeeId, _year + "-" + _month);
			}

		}

		leaveDetailsDtoList = processLeaveDetails(allLeaveDetails, leaveDetailsDtoList);
		return leaveDetailsDtoList;
	}

	@Override
	public String editLeaveRequest(int id, ManagerLeaveEditDto managerLeaveEditDto) {

		logger.info("Editing Leave Request of Id : {}", id);
		Double approvableDays = managerLeaveEditDto.getApprovedDaysForLeave();
		Optional<LeaveManagementTable> leaveDetails = this.leaveManagementRepo.findById(id);

		if (leaveDetails.isPresent()) {
			LeaveManagementTable leaveReq = leaveDetails.get();
			LocalDate leaveStartDate = leaveReq.getLeaveStartDate();
			LocalDate leaveEndDate = leaveReq.getLeaveEndDate();

//			for (LocalDate date = leaveStartDate; !date.isAfter(leaveEndDate); date = date.plusDays(1)) {
//				if (date.getDayOfWeek() == DayOfWeek.SUNDAY) {
//					approvableDays = approvableDays - 1;
//				}

			long numberOfSundays = leaveStartDate.datesUntil(leaveEndDate.plusDays(1))
					.filter(date_ -> date_.getDayOfWeek() == DayOfWeek.SUNDAY).count();
			approvableDays -= numberOfSundays;

			if (leaveReq.getAppliedDaysForLeave() > approvableDays) {
				try {
					leaveReq.setApprovedDaysForLeave(approvableDays);
					leaveReq.setRemarks(managerLeaveEditDto.getRemarks());

					this.leaveManagementRepo.save(leaveReq);
					String employeeId = leaveDetails.get().getEmployeeId();
					approveLeave(employeeId, approvableDays);

					return "Successfully saved";
				} catch (Exception e) {
					logger.error("Error occurred while editing leave request ", e);
					return "Error occurred while editing leave request";
				}
			} else {
				return "Approved Days should be less than Applied days!";
			}
		}
		return "Leave request with ID " + id + " not found";
	}

	private void approveLeave(String employeeId, Double approvedDays) {
		LeaveSummary leaveSummary = this.leaveSummaryRepository.findByEmployeeId(employeeId);

		if (leaveSummary != null) {
			float totalBalance = leaveSummary.getTotalBalance() - approvedDays.floatValue();
			leaveSummary.setTotalBalance(totalBalance);

			this.leaveSummaryRepository.save(leaveSummary);
		} else {
			logger.warn("Leave summary not found for employee with ID: {}", employeeId);
		}
	}

	public static LocalDate convertToLocalDate(Object dateToConvert) {
		if (dateToConvert != null) {
			return ((java.sql.Date) dateToConvert).toLocalDate();
		}
		return null;
	}

	/*
	 * SELECT DISTINCT pd.profile_photo, emp.name, emp.department, emp.designation,
	 * lmt.leave_type, lmt.leave_start_date, lmt.leave_end_date,
	 * lmt.applied_days_for_leave, lmt.leave_reason, m.name AS manager_name FROM
	 * leave_management_table lmt INNER JOIN employee emp ON emp.employee_id =
	 * lmt.employee_id INNER JOIN personal_details pd ON emp.email_id =
	 * pd.personal_mail_id LEFT JOIN employee m ON emp.manager = m.employee_id WHERE
	 * lmt.id = 8
	 */

//	@Override
//	public LeaveRequestDetailsDto getLeaveDetails(Integer id) {
//
//		logger.info("Inside get leave Details Method with id : {} ", id);
//		LeaveRequestDetailsDto leaveRequestDetailsDto = new LeaveRequestDetailsDto();
//
//		try {
//			Object[] leaveDetailsOptional = leaveManagementRepo.findLeaveDetailsById(id);
//
//			logger.info("leaveDetailsOptional[5] : {}", leaveDetailsOptional[5]);
//			logger.info("leaveDetailsOptional :: " + leaveDetailsOptional);
//			logger.info("leaveDetailsOptional[5] : {}", leaveDetailsOptional[5]);
//
//			// leaveRequestDetailsDto.setProfilePicture((byte[]) leaveDetailsOptional[9]);
//
//			try {
//				logger.debug("leaveDetailsOptional[5] : {}", leaveDetailsOptional[5]);
//				leaveRequestDetailsDto.setName((String) leaveDetailsOptional[5]);
//			} catch (Exception e) {
//				logger.error(e.getMessage());
//				e.printStackTrace();
//			}
//
//			Department department = mapByteToDepartment((leaveDetailsOptional)[7]);
//			leaveRequestDetailsDto.setDepartment(department);
//
//			Designation designation = mapByteToDesignation((leaveDetailsOptional)[8]);
//			leaveRequestDetailsDto.setDesignation(designation);
//
//			logger.debug("Leave Type = {}", mapByteToLeaveType((leaveDetailsOptional)[0]));
//
//			LeaveType leavetype = mapByteToLeaveType((leaveDetailsOptional)[0]);
//			leaveRequestDetailsDto.setLeaveType(leavetype);
//
//			LocalDate localDate1 = convertToLocalDate(leaveDetailsOptional[1]);
//			leaveRequestDetailsDto.setStartDate(localDate1);
//
//			LocalDate localDate = convertToLocalDate(leaveDetailsOptional[2]);
//			leaveRequestDetailsDto.setEndDate(localDate);
//
//			leaveRequestDetailsDto.setAppliedDaysForLeave((double) leaveDetailsOptional[3]);
//
//			leaveRequestDetailsDto.setReason((String) leaveDetailsOptional[4]);
//
//			leaveRequestDetailsDto.setManager((String) leaveDetailsOptional[6]);
//
////
////				Half half1 = mapByteToHalf((leaveDetailsOptional)[5]);
////				leaveRequestDetailsDto.setFirstHalf(half1);
////
////				Half half2 = mapByteToHalf((leaveDetailsOptional)[6]);
////				leaveRequestDetailsDto.setSecondHalf(half2);
//
////				leaveRequestDetailsDto.setManager((String) leaveDetailsOptional[16]);
//
////                
//
//			logger.info("leaveDetails :: " + leaveRequestDetailsDto);
//
//		} catch (Exception e) {
//			logger.error("An error occurred while fetching leave details for ID: {}", id, e);
//			throw new ServiceException("Error fetching leave details for ID: " + id, e);
//		}
//		return leaveRequestDetailsDto;
//	}

	private Designation mapByteToDesignation(Object leaveDetails) {

		if (leaveDetails == null) {
			return null;
		}

		int id = ((Byte) leaveDetails).intValue();
		switch (id) {
		case 0:
			return Designation.ASSOCIATE_ENGINEER;
		case 1:
			return Designation.ASSOCIATE_EXECUTIVE;
		case 2:
			return Designation.CXO;
		case 3:
			return Designation.JUNIOR_ENGINEER;
		case 4:
			return Designation.JUNIOR_EXECUTIVE;
		case 5:
			return Designation.PROGRAM_MANAGER;
		case 6:
			return Designation.PROJECT_MANAGER;
		case 7:
			return Designation.SENIOR_ENGINEER;
		case 8:
			return Designation.SENIOR_EXECUTIVE;
		case 9:
			return Designation.TEAM_LEAD;
		default:
			throw new IllegalArgumentException("Invalid Designation ID:" + id);
		}

	}

	private Departments.Department mapByteToDepartment(Object leaveDetails) {

		if (leaveDetails == null) {
			return null;
		}

		int id = ((Byte) leaveDetails).intValue();
		switch (id) {
		case 0:
			return Department.DIGITAL_FACTORY_SOLUTION;
		case 1:
			return Department.INDUSTRIAL_AUTOMATION_SOLUTION;
		case 2:
			return Department.ENGINEERING_DESIGN_SOLUTION;
		case 3:
			return Department.BUILDING_INFORMATION_MODELING;
		case 4:
			return Department.TALENT_ACQUISITION;
		case 5:
			return Department.HUMAN_RESOURCE;
		case 6:
			return Department.FINANCE;
		case 7:
			return Department.SALES;
		case 8:
			return Department.SYSTEM_ADMIN;
		case 9:
			return Department.INFORMATION_TECHNOLOGY;
		case 10:
			return Department.DIGITAL_MARKETING;
		case 11:
			return Department.DEVELOPMENT;
		default:
			throw new IllegalArgumentException("Invalid Department ID:" + id);

		}

	}

	private LeaveType mapByteToLeaveType(Object leaveDetails) {

		if (leaveDetails == null) {
			return null;
		}
		int id = ((Byte) leaveDetails).intValue();
		switch (id) {
		case 0:
			return LeaveType.Compensatory_Leave;
		case 1:
			return LeaveType.Earned_Leave;
		case 2:
			return LeaveType.Loss_Of_Pay;

		default:
			throw new IllegalArgumentException("Invalid LeaveType ID:" + id);
		}

	}

	private Half mapByteToHalf(Object leaveDetails) {

		if (leaveDetails == null) {
			return null;
		}
		int id = ((Byte) leaveDetails).intValue();
		switch (id) {
		case 0:
			return Half.First_Half;
		case 1:
			return Half.Second_Half;

		default:
			throw new IllegalArgumentException("Invalid Half ID:" + id);
		}

	}

	@Override
	public LeaveRequestDetailsDto getLeaveDetails(Integer id) {
		logger.info("Inside get leave Details Method with id : {} ", id);
		try {
			List<Object[]> result = leaveManagementRepo.findLeaveDetailsById(id);

			// logger.debug("Query result: {}", Arrays.toString(result));

//			for (Object[] row : result) {
//				logger.debug("Object in result array: {} of type {}", row, row.getClass().getName());
//				for (Object obj : row) {
//					String elementType = obj.getClass().getName();
//					logger.debug("Element : {} ; Element type : {}", obj, elementType);
//				}
//			}

//			if (result == null || result.length == 0) {
//				throw new LeaveDetailsNotFoundException("No leave details found for ID: " + id);
//			}

			// Correctly cast each element from the result array

			Object[] objects = result.get(0);

			LeaveType leaveType = LeaveType.values()[(Byte) objects[0]];
			LocalDate leaveStartDate = ((Date) objects[1]).toLocalDate();
			LocalDate leaveEndDate = ((Date) objects[2]).toLocalDate();
			Double appliedDaysForLeave = (Double) objects[3];
			String leaveReason = (String) objects[4];
			String employeeName = (String) objects[5];
			String managerName = (String) objects[6];
			Department department = mapByteToDepartment(objects[7]);
			Designation designation = mapByteToDesignation(objects[8]);
			byte[] profilePhoto = (byte[]) objects[9];

			LeaveRequestDetailsDto dto = new LeaveRequestDetailsDto();
			dto.setLeaveType(leaveType);
			dto.setStartDate(leaveStartDate);
			dto.setEndDate(leaveEndDate);
			dto.setAppliedDaysForLeave(appliedDaysForLeave);
			dto.setReason(leaveReason);
			dto.setName(employeeName);
			dto.setManager(managerName);
			dto.setDepartment(department);
			dto.setDesignation(designation);
			dto.setProfilePicture(profilePhoto);

			return dto;

		} catch (ArrayIndexOutOfBoundsException e) {
			logger.error("ArrayIndexOutOfBoundsException while fetching leave details for ID: {}", id, e);
			throw new ServiceException("An error occurred while fetching leave details for ID: " + id, e);
		} catch (ClassCastException e) {
			logger.error("ClassCastException while fetching leave details for ID: {}", id, e);
			throw new ServiceException("Type casting error while fetching leave details for ID: " + id, e);
		} catch (Exception e) {
			logger.error("Exception while fetching leave details for ID: {}", id, e);
			throw new ServiceException("An error occurred while fetching leave details for ID: " + id, e);
		}
	}
}