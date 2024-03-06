package com.hrm.servicesImpls;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hrm.Helper.EnumCollection.AttendanceStatus;
import com.hrm.models.Attendance;
import com.hrm.models.Employee;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.repositories.IAttendanceRepository;
import com.hrm.repositories.IEmployeeRepository;
import com.hrm.services.IAttendanceService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AttendanceServiceImpl implements IAttendanceService {

	@Autowired
	IAttendanceRepository attendanceRepository;

	@Autowired
	IEmployeeRepository employeeRepository;

	private static final Logger logger = LoggerFactory.getLogger(IAttendanceService.class);

	@Override
	public String clockIn(String employeeId) {

		Attendance existingAttendance = this.attendanceRepository.findByEmployeeIdAndDate(employeeId, LocalDate.now());

		if (existingAttendance != null) {
			return "Clock-in record already exist for Employee Id : " + employeeId;
		} else {
			Attendance attendance = new Attendance();// new api getclock in timee(emp id , now time and current time)

			attendance.setEmployeeId(employeeId);
			attendance.setMonth(LocalDateTime.now().getMonth());
			attendance.setDate(LocalDate.now());
			attendance.setInTime(LocalTime.now());
			attendance.setAttendanceStatus(AttendanceStatus.Present);
			this.attendanceRepository.save(attendance);
			return "attendence added of Employee Id : " + employeeId;
		}

	}

	@Override
	public String clockOut(String employeeId) {

		Attendance attendance = this.attendanceRepository.findByEmployeeId(employeeId);

		attendance.setOutTime(LocalTime.now());
		attendance.setWorkHrs(Duration.between(attendance.getOutTime(), attendance.getInTime()));
		this.attendanceRepository.save(attendance);
		return "Check Out of Employee Id " + employeeId + " at " + attendance.getOutTime();

	}

	@Override
	public List<Attendance> allAttendance(String employeeId) {
		List<Attendance> allByEmployeeId = attendanceRepository.findAllByEmployeeId(employeeId);

		if (allByEmployeeId.isEmpty()) {

			return Collections.emptyList();
		}

		return allByEmployeeId;
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

	@Override
//<<<<<<< HEAD
	public String addBillableHours(BillableHoursDto billableHoursDto, String employeeId) {
		List<Attendance> attendanceListOfEmployee = this.attendanceRepository.findAllByEmployeeId(employeeId);
		List<LocalDate> listOfDate = billableHoursDto.getListOfDate();

		for (LocalDate date : listOfDate) {

		}

		return null;
	}

	@Override
	public String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId) {
		return null;
	}

//=======
	public ApplyLeaveDto createOrUpdateLeave(ApplyLeaveDto leaveDto) {
		if (leaveDto.getEmployeeId() == null) {
			throw new IllegalArgumentException("Employee ID cannot be null.");
		}

		String employeeId = leaveDto.getEmployeeId();
		Attendance attendance = attendanceRepository.findByEmployeeId(employeeId);
		if (attendance == null) {
			throw new IllegalStateException("Attendance record not found for employee: " + employeeId);
		}

		if (!"P".equals(attendance.getAttendanceStatus()) && !"Weekoff".equals(attendance.getAttendanceStatus())
				&& !"Holiday".equals(attendance.getAttendanceStatus())) {

			leaveDto.setStartDate(leaveDto.getStartDate());
			leaveDto.setEndDate(leaveDto.getEndDate());
			leaveDto.setLeaveType(leaveDto.getLeaveType());
			leaveDto.setLeaveReason(leaveDto.getLeaveReason());
			leaveDto.setHalf1(leaveDto.getHalf1());
			leaveDto.setHalf2(leaveDto.getHalf2());

			// Save or update the attendance record
//	        attendanceRepository.save(attendance);

			// Update the status in the attendance record
//	        attendance.setAttendanceStatus("Pending");
			attendanceRepository.save(attendance);
			return leaveDto;
		} else {
			return null;
		}
	}

//	@Override
//	public ApplyLeaveDto getLeave(String employeeId) {
//		// TODO Auto-generated method stub
//		return null;
//	}

//>>>>>>> branch 'ramachandra' of https://github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git

}
