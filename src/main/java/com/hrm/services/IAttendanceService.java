package com.hrm.services;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hrm.models.Attendance;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.UserAttendanceDto;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<UserAttendanceDto> allAttendance(String employeeId);
	
	

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

	String addBillableHours(BillableHoursDto billableHoursDto, String employeeId);

	String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId);

//	String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId);
	
//	ApplyLeaveDto getLeave(String employeeId);
	
	RegularizationHoursDto getRegularizationHours(String employeeId, LocalDate date);
	
	BillableHoursDto getBillableHours(String employeeId, LocalDate date);

	String getAttendanceAsJson(String managerId, String month);
	
	ManagerAttendanceViewDto editManagerAttendance(ManagerAttendanceViewDto managerAttendanceViewDto, String employeeId);
	
//	String addManagerAttendance(ManagerAttendanceViewDto managerAttendanceViewDto, LocalDate date);
//	
//	void updateManagerAttendance(ManagerAttendanceViewDto managerAttendanceViewDto, LocalDate date);

//	ApplyLeaveDto addLeave(ApplyLeaveDto leaveDto);

//	float gettotalHoursFromBillableHoursDto(String employeeId);

//	LocalTime getinTimeFromAttendance(String employeeId);

//	LocalTime getoutTimeFromAttendance(String employeeId);

}
