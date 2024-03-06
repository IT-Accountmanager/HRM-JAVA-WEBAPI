package com.hrm.services;

import java.util.List;

import com.hrm.models.Attendance;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.RegularizationHoursDto;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<Attendance> allAttendance(String employeeId);

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);
	
//	ApplyLeaveDto getLeave(String employeeId);

	ApplyLeaveDto createOrUpdateLeave(ApplyLeaveDto leaveDto);

	String addBillableHours(BillableHoursDto billableHoursDto, String employeeId);

	String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId);

}
