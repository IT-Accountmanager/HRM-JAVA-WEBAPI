package com.hrm.servicesImpls;

import java.time.LocalDate;
import java.time.Month;
import java.time.Year;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
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
import com.hrm.models.PersonalDetails;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.LeaveDetailsRequestDto;
import com.hrm.payloads.LeaveRequestDetailsDto;
import com.hrm.payloads.ManagerLeaveDetailsDto;
import com.hrm.payloads.ManagerLeaveEditDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.SubDepartmentAndName;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.repositories.IPersonalDetailsRepository;
import com.hrm.repositories.LeaveManagementRepo;
import com.hrm.services.LeaveManagementService;

@Service

public class LeaveManagementServiceImpl implements LeaveManagementService {
	@Autowired
	LeaveManagementRepo leaveManagementRepo;

	@Autowired
	IEmployeeRepository employeeRepository;

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
		Optional<LeaveManagementTable> leaveDetails = this.leaveManagementRepo.findById(id);

		if (leaveDetails.isPresent()) {
			LeaveManagementTable leaveReq = leaveDetails.get();

			leaveReq.setApprovedDaysForLeave(managerLeaveEditDto.getApprovedDaysForLeave());
			leaveReq.setRemarks(managerLeaveEditDto.getRemarks());

			this.leaveManagementRepo.save(leaveReq);

			return "Successfully saved";
		} else {
			return "Leave request with ID " + id + " not found";
		}
	}

	public static LocalDate convertToLocalDate(Object dateToConvert) {
		if (dateToConvert!= null) {
		return ((java.sql.Date)dateToConvert).toLocalDate();
		}
		return null;
	}

	@Override
	public List<LeaveRequestDetailsDto> getLeaveDetails(Integer id) {
		
		List<LeaveRequestDetailsDto> listLeaveRequestDetailsDto = new ArrayList<>();
		
		
		try {
			List<Object[]> listLeaveDetailsOptional = leaveManagementRepo.findLeaveDetailsById(id);

			for (Object[] leaveDetailsOptional : listLeaveDetailsOptional) {

				logger.info("leaveDetailsOptional :: " + leaveDetailsOptional);



				LeaveRequestDetailsDto leaveRequestDetailsDto = new LeaveRequestDetailsDto();

				leaveRequestDetailsDto.setAppliedDaysForLeave((double) leaveDetailsOptional[1]);

				LocalDate localDate = convertToLocalDate( leaveDetailsOptional[7]);
				leaveRequestDetailsDto.setEndDate(localDate);


				Half half1 = mapByteToHalf((leaveDetailsOptional)[5]);
				leaveRequestDetailsDto.setFirstHalf(half1);

				Half half2 = mapByteToHalf((leaveDetailsOptional)[6]);
				leaveRequestDetailsDto.setSecondHalf(half2);

				LeaveType leavetype = mapByteToLeaveType((leaveDetailsOptional)[10]);
				leaveRequestDetailsDto.setLeaveType(leavetype);

				LocalDate localDate1 = convertToLocalDate( leaveDetailsOptional[9]);
				leaveRequestDetailsDto.setStartDate(localDate1);
				leaveRequestDetailsDto.setReason((String) leaveDetailsOptional[8]);

				
				leaveRequestDetailsDto.setName((String) leaveDetailsOptional[15]);
				leaveRequestDetailsDto.setManager((String) leaveDetailsOptional[16]);

//                Department department = mapByteToDepartment((leaveDetailsOptional)[17]);
//                leaveRequestDetailsDto.setDepartment(department);

				
                
				Designation designation = mapByteToDesignation((leaveDetailsOptional)[18]);
				leaveRequestDetailsDto.setDesignation(designation);


				leaveRequestDetailsDto.setProfilePicture((byte[]) leaveDetailsOptional[19]);

				logger.info("leaveDetails :: " + leaveRequestDetailsDto);

				listLeaveRequestDetailsDto.add(leaveRequestDetailsDto);
			}

		} catch (Exception e) {
			logger.error("An error occurred while fetching leave details for ID: {}", id, e);
			throw new ServiceException("Error fetching leave details for ID: " + id, e);
		}
		return listLeaveRequestDetailsDto;
	}

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

//	private Departments.Department mapByteToDepartment(Object leaveDetails) {
//		
//		if (leaveDetails == null) {
//			return null;
//		}
//		
//		int id = ((Byte) leaveDetails).intValue();
//		switch (id) {
//		case 0:
//			return Department.DIGITAL_FACTORY_SOLUTION;
//		case 1:
//			return Department.INDUSTRIAL_AUTOMATION_SOLUTION;
//		case 2:
//			return Department.ENGINEERING_DESIGN_SOLUTION;
//		case 3:
//			return Department.BUILDING_INFORMATION_MODELING;
//		case 4:
//			return Department.TALENT_ACQUISITION;
//		case 5:
//			return Department.HUMAN_RESOURCE;
//		case 6:
//			return Department.FINANCE;
//		case 7:
//			return Department.SALES;
//		case 8:
//			return Department.SYSTEM_ADMIN;
//		case 9:
//			return Department.INFORMATION_TECHNOLOGY;
//		case 10:
//			return Department.DIGITAL_MARKETING;
//		case 11:
//			return Department.DEVELOPMENT;
//		default:
//			throw new IllegalArgumentException("Invalid Department ID:" + id);
//			
//		}
//	
//	}

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
}