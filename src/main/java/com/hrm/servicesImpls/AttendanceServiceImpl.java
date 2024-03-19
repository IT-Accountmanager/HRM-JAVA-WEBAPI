package com.hrm.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
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
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.services.IAttendanceService;

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
				attendance.setAttendanceStatus(AttendanceStatus.Present);

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
			dto.setAttendanceStatus(attendance.getAttendanceStatus());
			dto.setManager(employee.getManager());
			dto.setProjectId(null);
			dto.setAppliedHoursForBilling(attendance.getAppliedHrsForBilling());
			dto.setApprovedHoursForBilling(attendance.getApprovedHrsForBilling());
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

	    
	    double productionHoursDouble = Double.parseDouble(productionHours);
	    double otherHoursDouble = Double.parseDouble(otherHours);

	    
	    double totalHoursDouble = productionHoursDouble + otherHoursDouble;

	    
	    int totalHoursInt = (int) totalHoursDouble;
	    int minutes = (int) ((totalHoursDouble - totalHoursInt) * 60); // Get remaining minutes
	    String totalHoursFormatted = String.format("%02d:%02d", totalHoursInt, minutes);

	   
	    LocalDate currentDate = LocalDate.now();

	    
	    Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, currentDate);

	    if (attendance != null) {
	        
	        attendance.setProductionHours(String.valueOf(productionHoursDouble));
	        attendance.setOtherHours(String.valueOf(otherHoursDouble));
	        attendance.setTotalHours(totalHoursFormatted); 
	       
	        attendanceRepository.save(attendance);

	
	        return "Billable hours added for employee " + employeeId + " on " + currentDate;
	    } else {
	        return "Attendance record not found for employee " + employeeId + " on " + currentDate;
	    }
	}

	
//	@Override
//	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
//		LocalDate date = billableHoursDto.getDate();
//		float productionHours = billableHoursDto.getProductionHours();
//		float otherHours = billableHoursDto.getOtherHours();
//		float totalHours = billableHoursDto.getAppliedHrsForBilling();
//
//
//		Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);
//
//		if (attendance != null) {
//
//			attendance.setProductionHours(productionHours);
//			attendance.setOtherHours(otherHours);
//			attendance.setAppliedHrsForBilling(totalHours);
//
//			attendanceRepository.save(attendance);
//			return "Billable hours added for employee " + employeeId + " on " + date;
//		} else {
//			return "Attendance not existed for employee " + employeeId + " on " + date;
//		}
//	}

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
		LocalDate date = regularizationHoursDto.getDate();
		LocalTime inTime = regularizationHoursDto.getInTime();
		LocalTime outTime = regularizationHoursDto.getOutTime();
		String regularisationReason = regularizationHoursDto.getRegularisationReason();

		// Calculate the duration between inTime and outTime
		Duration difference = Duration.between(inTime, outTime);

		// Define the threshold duration of 9 hours and 30 minutes
		Duration threshold = Duration.ofHours(9).plusMinutes(30);

		// Fetch the existing attendance record for the employee
		Attendance attendance = attendanceRepository.findByEmployeeIdAndDate(employeeId, date);

		if (attendance != null) {
			// Update the attendance details
			attendance.setDate(date);
			attendance.setInTime(inTime);
			attendance.setOutTime(outTime);
			attendance.setRegularisationReason(regularisationReason);

			// Calculate the remaining regularization hours
			Duration remainingHours = difference.minus(threshold);

			// Check if remaining hours are negative and set to zero in that case
			attendance.setRegularisationRequestHours(remainingHours.isNegative() ? Duration.ZERO : remainingHours);

			// Save the updated attendance record
			attendanceRepository.save(attendance);

			return "Regularization hours added for employee " + employeeId;
		} else {
			return "Attendance record not found for employee " + employeeId;
		}
	}

	@Override
	public String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId) {

		LeaveType leaveType = applyLeaveDto.getLeaveType();
		LocalDate startDate = applyLeaveDto.getStartDate();
		LocalDate endDate = applyLeaveDto.getEndDate();
		Half half1 = applyLeaveDto.getHalf1();
		Half half2 = applyLeaveDto.getHalf2();
		String leaveReason = applyLeaveDto.getLeaveReason();

		Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
		if (attendance == null) {
			attendance = new Attendance();
			attendance.setEmployeeId(employeeId);
		}

		attendance.setLeaveType(leaveType);
		attendance.setStartDate(startDate);
		attendance.setEndDate(endDate);
		attendance.setHalf1(half1);
		attendance.setHalf2(half2);
		attendance.setLeaveReason(leaveReason);

		attendanceRepository.save(attendance);

		return "Leave Added Successfully for employee Id " + employeeId;
	}
	
	
	
	@Override
	public ApplyLeaveDto getLeave(String employeeId) {
	    // Assuming LeaveRepository has a method to find leave by employeeId
	    Attendance leave = attendanceRepository.findByEmployeeId(employeeId);
	    // Map Leave entity to ApplyLeaveDto
	    if (leave != null) {
	        ApplyLeaveDto applyLeaveDto = new ApplyLeaveDto();
	        applyLeaveDto.setHalf1(leave.getHalf1());
	        applyLeaveDto.setHalf2(leave.getHalf2());
	        applyLeaveDto.setLeaveReason(leave.getLeaveReason());
	        applyLeaveDto.setLeaveType(leave.getLeaveType());
	        applyLeaveDto.setStartDate(leave.getStartDate());
	        applyLeaveDto.setEndDate(leave.getEndDate());
	        // Set other fields as needed
	        return applyLeaveDto;
	    } else {
	        throw new RuntimeException("Leave Record is not found for employee " + employeeId);
	    }
	}

	@Override
	public BillableHoursDto getBillableHours(String employeeId) {
		Attendance billableHours = attendanceRepository.findByEmployeeId(employeeId);
		
		if(billableHours != null) {
			BillableHoursDto billableHoursDto = new BillableHoursDto();
			
			
			billableHoursDto.setProductionHours(billableHours.getProductionHours());
			billableHoursDto.setOtherHours(billableHours.getOtherHours());
			billableHoursDto.setAppliedHrsForBilling(billableHours.getAppliedHrsForBilling());
			
			return billableHoursDto;
		}
		
		else {
			throw new RuntimeException("Billable Hours Record is not found for employee " + employeeId);
		}
	}

	@Override
	public RegularizationHoursDto getRegularizationHours(String employeeId) {
		Attendance regularizationHours = attendanceRepository.findByEmployeeId(employeeId);
		
		if(regularizationHours != null) {
			RegularizationHoursDto regularizationHoursDto = new RegularizationHoursDto();
			
			regularizationHoursDto.setInTime(regularizationHours.getInTime());
			regularizationHoursDto.setOutTime(regularizationHours.getOutTime());
			regularizationHoursDto.setRegularisationReason(regularizationHours.getRegularisationReason());
			regularizationHoursDto.setRegularisationRequestHours(regularizationHours.getRegularisationRequestHours());
			
			return regularizationHoursDto;
			
		}
		else {
			throw new RuntimeException("Regularization Hours Record is not found for employee " + employeeId);
		}
	}

}