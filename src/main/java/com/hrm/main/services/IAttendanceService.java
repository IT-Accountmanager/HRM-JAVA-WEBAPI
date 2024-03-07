package com.hrm.main.services;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

import com.hrm.main.models.Attendance;
import com.hrm.main.models.Employee;
import com.hrm.main.payloads.ApplyLeaveDto;
import com.hrm.main.payloads.AttendanceEmployeeDto;
import com.hrm.main.payloads.AttendanceHRManagerDto;
import com.hrm.main.payloads.BillableHoursDto;
import com.hrm.main.payloads.RegularizationHoursDto;

public interface IAttendanceService {

	String clockIn(String employeeId, LocalDate date);

	String clockOut(String employeeId);

	List<Attendance> allAttendance(String employeeId);

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

//	ApplyLeaveDto getLeave(String employeeId);

	ApplyLeaveDto addLeave(ApplyLeaveDto leaveDto);

	String addBillableHours(BillableHoursDto billableHoursDto, String employeeId);

	String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId);

//	float gettotalHoursFromBillableHoursDto(String employeeId);
	
//	LocalTime getinTimeFromAttendance(String employeeId);
	
//	LocalTime getoutTimeFromAttendance(String employeeId);
	
	

}
