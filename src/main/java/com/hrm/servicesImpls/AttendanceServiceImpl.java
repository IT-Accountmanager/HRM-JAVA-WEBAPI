package com.hrm.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.hrm.helper.EnumCollection.AttendanceStatus;
import com.hrm.helper.Format;
import com.hrm.helper.EnumCollection.Half;
import com.hrm.helper.EnumCollection.LeaveType;
import com.hrm.models.Attendance;
import com.hrm.models.Employee;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BasicInfoDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.services.IAttendanceService;

import jakarta.persistence.EntityNotFoundException;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(IAttendanceService.class);

	/*
	 * @Override public String clockIn(String employeeId) {
	 * 
	 * Attendance existingAttendance =
	 * this.attendanceRepository.findByEmployeeIdAndDate(employeeId,
	 * LocalDate.now());
	 * 
	 * if (existingAttendance != null) { return
	 * "Clock-in record already exist for Employee Id : " + employeeId; } else {
	 * Attendance attendance = new Attendance();// new api getclock in timee(emp id
	 * , now time and current time)
	 * 
	 * attendance.setEmployeeId(employeeId);
	 * attendance.setMonth(LocalDateTime.now().getMonth());
	 * attendance.setDate(LocalDate.now()); attendance.setInTime(LocalTime.now());
	 * attendance.setAttendanceStatus(AttendanceStatus.Present);
	 * this.attendanceRepository.save(attendance); return
	 * "attendence added of Employee Id : " + employeeId; }
	 * 
	 * }
	 */

	@Override
	public String clockIn(String employeeId) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
			String formattedTime = LocalTime.now().format(formatter);
			LocalDate currentDate = LocalDate.now();

			LocalTime parsedTime = LocalTime.parse(formattedTime, formatter);

			Attendance existingAttendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, currentDate);
			if (existingAttendance != null) {
				logger.info("Clock-in record already exists for Employee Id: {}", employeeId);
				return "Clock-in record already exists for Employee Id : " + employeeId;
			} else {
				logger.info("Clock-in record not present for the employee Id: {}", employeeId);

				Attendance attendance = new Attendance();
				attendance.setEmployeeId(employeeId);
				attendance.setMonth(LocalDateTime.now().getMonth());
				attendance.setDate(currentDate);
				attendance.setInTime(LocalTime.now());
				// attendance.setInTime(parsedTime);
				// attendance.setAttendanceStatus(AttendanceStatus.Present);

				this.attendanceRepository.save(attendance);

				logger.info("Attendance added for Employee Id: {}", employeeId);
				return "Attendance added for Employee Id : " + employeeId;
			}
		} catch (Exception e) {
			logger.error("Error occurred while clocking in for Employee Id: {}", employeeId, e);
			return "Error occurred while clocking in for Employee Id : " + employeeId;
		}
	}

	@Override
	public String clockOut(String employeeId) {
		LocalDate date = LocalDate.now();
		Attendance attendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
		LocalTime outTime = LocalTime.now();

		// Format the out time to HH:MM format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
		String formattedOutTime = outTime.format(formatter);

		attendance.setOutTime(outTime);
		Duration workDuration = Duration.between(attendance.getInTime(), outTime);

		long nanoseconds = workDuration.toNanos();
		double hours = nanoseconds / (double) Duration.ofHours(1).toNanos();

		attendance.setWorkHrs(Duration.ofHours((long) hours));

		this.attendanceRepository.save(attendance);

		return "Check Out of Employee Id " + employeeId + " at " + formattedOutTime;
	}

	@Override
	public List<UserAttendanceDto> allAttendance(String employeeId) {
		List<Attendance> allByEmployeeId = attendanceRepository.findAllByEmployeeId(employeeId);

		if (allByEmployeeId.isEmpty()) {
			return Collections.emptyList();
		}

		List<UserAttendanceDto> attendanceDtoList = new ArrayList<>();

		for (Attendance attendance : allByEmployeeId) {
			UserAttendanceDto attendanceDto = new UserAttendanceDto();

			// Employee employee = this.employeeRepository.findByEmployeeId(employeeId);

			attendanceDto.setEmployeeId(employeeId);
			attendanceDto.setMonth(getMonthName(attendance.getMonth()));
			attendanceDto.setDate(Format.getFormattedDate(attendance.getDate()));
			attendanceDto.setInTime(Format.getFormattedTime(attendance.getInTime()));
			attendanceDto.setOutTime(Format.getFormattedTime(attendance.getOutTime()));
//			remarks edited
			
			attendanceDto.setRemarks(attendance.getRemarks());
			if (attendance.getInTime() != null && attendance.getOutTime() != null) {
				attendanceDto.setWorkHrs(Format
						.getFormattedWorkHours(Duration.between(attendance.getInTime(), attendance.getOutTime())));
			} else {
				attendanceDto.setWorkHrs(Format.getFormattedWorkHours(Duration.ZERO));
			}

			attendanceDto.setAttendanceStatus(attendance.getAttendanceStatus());
			// attendanceDto.setManager(employee.getManager()); // Set manager value based
			// on your logic
			attendanceDto.setProjectId(attendance.getProjectId());
			attendanceDto.setAppliedHrsForBilling(String.valueOf(attendance.getAppliedHrsForBilling()));
			attendanceDto.setApprovedHrsForBilling(String.valueOf(attendance.getApprovedHrsForBilling()));
			// attendanceDto.setRemarks(""); // Set remarks value based on your logic

			attendanceDtoList.add(attendanceDto);
		}

		return attendanceDtoList;
	}

	private String getMonthName(Month month) {
		return (month != null) ? month.name() : "Month Not Set";
	}

	@Override
	public AttendanceEmployeeDto getAttendance(String employeeId) {
		Employee employee = this.employeeRepository.findByEmployeeId(employeeId);
		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);
		AttendanceEmployeeDto dto = new AttendanceEmployeeDto();

		try {

			logger.info("Attempting to retrieve attendance for employee with ID: {}", employeeId);

			dto.setEmployeeId(employeeId);
			dto.setMonth(attendance.getMonth());
			dto.setDate(attendance.getDate());
			dto.setInTime(attendance.getInTime());
			dto.setOutTime(attendance.getOutTime());
			dto.setWorkHours(dto.calculateWorkHours());
			dto.setWorkHrs(dto.formatWorkHours());
			dto.setAttendanceStatus((Character) null);
			dto.setManager(null);
			dto.setProjectId(null);
			dto.setAppliedHoursForBilling(null);
			dto.setApprovedHoursForBilling(null);
			dto.setBillingHoursStatus(null);

			logger.info("Attendance retrieved successfully for employee with ID: {}", employeeId);

			return dto;
		} catch (Exception e) {
			logger.error("An error occurred while processing attendance for employee with ID: {}", employeeId, e);

			// Optionally, you can rethrow the exception or return a default/empty DTO
			throw new RuntimeException("Error retrieving attendance information for employee with ID: " + employeeId,
					e);
		}
	}

	@Override
	public String deleteAttendance(Integer id) {
		try {
			attendanceRepository.deleteById(id);

			return "Id no. " + id + " is deleted succesfully. ";

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not deleted. ";
	}

	@Override
	public String editAttendance(Attendance attendance, Integer id) {

		try {
			if (this.attendanceRepository.existsById(id)) {
				// attendance.setId(id);
				this.attendanceRepository.save(attendance);
				return "Id no. " + id + " is updated. ";
			} else {
				return "Id no. " + id + " is does not exists. ";
			}

		} catch (Exception e) {
			e.getMessage();
		}
		return "Id no. " + id + " is not updated. ";
	}

//	@Override
//<<<<<<< HEAD
//	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
//		List<Attendance> attendanceListOfEmployee = this.attendanceRepository.findAllByEmployeeId(employeeId);
//		List<LocalDate> listOfDate = billableHoursDto.getListOfDate();
//
//		for (LocalDate date : listOfDate) {
//
//		}
//
//		return null;
//	}

//	@Override
//	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
//		return null;
//	}



	@Override
	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
		LocalDate date = billableHoursDto.getDate();
		String productionHours = billableHoursDto.getProductionHours();
		String otherHours = billableHoursDto.getOtherHours();
		String appliedHrsForBilling = billableHoursDto.getAppliedHrsForBilling();


		Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (attendance != null) {

			attendance.setProductionHours(productionHours);
			attendance.setOtherHours(otherHours);
			attendance.setAppliedHrsForBilling(appliedHrsForBilling);

			attendanceRepository.save(attendance);
			return "Billable hours added for employee " + employeeId + " on " + date;
		} else {
			return "Attendance not existed for employee " + employeeId + " on " + date;
		}
	}


	/*
	 * @Override public String addRegularizationHours(RegularizationHoursDto
	 * regularizationHoursDto, String employeeId) { LocalDate date =
	 * regularizationHoursDto.getDate(); LocalTime inTime =
	 * regularizationHoursDto.getInTime(); LocalTime outTime =
	 * regularizationHoursDto.getOutTime(); String regularisationReason =
	 * regularizationHoursDto.getRegularisationReason();
	 * 
	 * Duration difference = Duration.between(inTime, outTime);
	 * 
	 * // Define the threshold duration of 9 hours and 30 minutes Duration threshold
	 * = Duration.ofHours(9).plusMinutes(30); Attendance attendance =
	 * attendanceRepository.findByEmployeeId(employeeId);
	 * 
	 * if (attendance != null) { attendance.setDate(date);
	 * attendance.setInTime(inTime); attendance.setOutTime(outTime);
	 * attendance.setRegularisationReason(regularisationReason);
	 * 
	 * long nanoseconds = difference.toNanos(); double hours = nanoseconds /
	 * (double) Duration.ofHours(1).toNanos();
	 * 
	 * if (difference.compareTo(threshold) < 0) {
	 * 
	 * Duration remainingHours = threshold.minus(difference);
	 * attendance.setRegularisationRequestHours(remainingHours); } else {
	 * attendance.setRegularisationRequestHours(Duration.ZERO); }
	 * 
	 * attendanceRepository.save(attendance); return
	 * "Regularization hours added for employee " + employeeId; } else { return
	 * "Attendance record not found for employee " + employeeId; } }
	 */

	@Override
	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
	    LocalTime inTime = regularizationHoursDto.getInTime();
	    LocalTime outTime = regularizationHoursDto.getOutTime();
	    String regularisationReason = regularizationHoursDto.getRegularisationReason();
	    
	    // Calculate the duration between exactInTime and exactOutTime
	    Duration difference = Duration.between(inTime, outTime);
	    
	    // Define the threshold duration of 9 hours and 30 minutes
	    Duration threshold = Duration.ofHours(9).plusMinutes(30);

	    Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);

	    if (attendance != null) {
	        attendance.setInTime(inTime);
	        attendance.setOutTime(outTime);
	        attendance.setRegularisationReason(regularisationReason);
	        
	        // Check if the duration is less than the threshold
	        if (difference.compareTo(threshold) < 0) {
	            // If less, set RegularisationRequestHours to the difference from the threshold
	            Duration remainingTime = threshold.minus(difference);
	            
	            // Convert remainingTime to hours and minutes
	            long remainingHours = remainingTime.toHours();
	            long remainingMinutes = remainingTime.minusHours(remainingHours).toMinutes();

	            // Set RegularisationRequestHours as a formatted string
	            attendance.setRegularisationRequestHours(String.format("%02d:%02d", remainingHours, remainingMinutes));
	        } else {
	            // If greater or equal, set RegularisationRequestHours to zero
	            attendance.setRegularisationRequestHours("00:00");
	        }

	        attendanceRepository.save(attendance);
	        return "Regularization hours added for employee " + employeeId;
	    } else {
	        return "Attendance record not found for employee " + employeeId;
	    }
	}



//	@Override
//	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {
//
//	    LeaveType leaveType = applyLeaveDto.getLeaveType();
//	    LocalDate startDate = applyLeaveDto.getStartDate();
//	    LocalDate endDate = applyLeaveDto.getEndDate();
//	    Half half1 = applyLeaveDto.getHalf1();
//	    Half half2 = applyLeaveDto.getHalf2();
//	    String leaveReason = applyLeaveDto.getLeaveReason();
//
//	    Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
//	    if (attendance == null) {
//	        attendance = new Attendance();
//	        attendance.setEmployeeId(employeeId);
//	    }
//
//	    attendance.setLeaveType(leaveType);
//	    attendance.setStartDate(startDate);
//	    attendance.setEndDate(endDate);
//	    attendance.setHalf1(half1);
//	    attendance.setHalf2(half2);
//	    attendance.setLeaveReason(leaveReason);
//
//	
//
//	    // Fetch the month from start date
//	    Month startMonth = startDate.getMonth();
//	    // Fetch the month from end date
//	    Month endMonth = endDate.getMonth();
//	    
//	    // Calculate the number of days between start date and end date
//	    long numberOfDays = ChronoUnit.DAYS.between(startDate, endDate);
//
//	    attendanceRepository.save(attendance);
//
//	    return "Leave Added Successfully for employee Id " + employeeId + ". Number of days: " + numberOfDays +
//	            ". Start Month: " + startMonth + ". End Month: " + endMonth;
//	}

//	@Override
//	public ApplyLeaveDto getLeave(String employeeId) {
//		// Assuming LeaveRepository has a method to find leave by employeeId
//		Attendance leave = attendanceRepository.findByEmployeeId(employeeId);
//		// Map Leave entity to ApplyLeaveDto
//		if (leave != null) {
//			ApplyLeaveDto applyLeaveDto = new ApplyLeaveDto();
//			applyLeaveDto.setHalf1(leave.getHalf1());
//			applyLeaveDto.setHalf2(leave.getHalf2());
//			applyLeaveDto.setLeaveReason(leave.getLeaveReason());
//			applyLeaveDto.setLeaveType(leave.getLeaveType());
//			applyLeaveDto.setStartDate(leave.getStartDate());
//			applyLeaveDto.setEndDate(leave.getEndDate());
//			// Set other fields as needed
//			return applyLeaveDto;
//		} else {
//			throw new RuntimeException("Leave Record is not found for employee " + employeeId);
//		}
//	}

	@Override
	public BillableHoursDto getBillableHours(String employeeId,LocalDate date) {
		Attendance billableHours = attendanceRepository.findByEmployeeId(employeeId);

		if (billableHours != null) {
			BillableHoursDto billableHoursDto = new BillableHoursDto();

			billableHoursDto.setProductionHours(billableHours.getProductionHours());
			billableHoursDto.setOtherHours(billableHours.getOtherHours());
			billableHoursDto.setAppliedHrsForBilling(billableHours.getAppliedHrsForBilling());

			return billableHoursDto;
		}

		else {
			return null;
		}
	}

	@Override
	public RegularizationHoursDto getRegularizationHours(String employeeId, LocalDate date) {
	    Attendance regularizationHours = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

	    if (regularizationHours != null) {
	        RegularizationHoursDto regularizationHoursDto = new RegularizationHoursDto();

	        regularizationHoursDto.setInTime(regularizationHours.getInTime());
	        regularizationHoursDto.setOutTime(regularizationHours.getOutTime());
	        regularizationHoursDto.setRegularisationReason(regularizationHours.getRegularisationReason());
	        regularizationHoursDto.setRegularisationRequestHours(regularizationHours.getRegularisationRequestHours());

	        return regularizationHoursDto;
	    } 
	    
	    else {
	         return null;
	    }
	}


	@Override
	public String getAttendanceAsJson(String managerId, String month) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ManagerAttendanceViewDto editManagerAttendance(ManagerAttendanceViewDto managerAttendanceViewDto,String employeeId) {
		Attendance managerAttendance = this.attendanceRepository.findByEmployeeId(employeeId);
		
		
		 ManagerAttendanceViewDto updatedDto = new ManagerAttendanceViewDto();
		 
		managerAttendance.setApprovedHrsForBilling(managerAttendanceViewDto.getApprovedHrsForBilling());
		managerAttendance.setRemarks(managerAttendanceViewDto.getRemarks());
		
		this.attendanceRepository.save(managerAttendance);
		
		return updatedDto;

	}
	

	

}