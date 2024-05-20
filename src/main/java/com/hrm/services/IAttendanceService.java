package com.hrm.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hrm.models.Attendance;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.AttendanceRequestDto;
import com.hrm.payloads.AttendanceSummaryDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceDetailsDto;
import com.hrm.payloads.ManagerAttendanceEditDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.RegularizationManagerEditDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.payloads.UserAttendanceSummaryDto;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	Set<UserAttendanceDto> allAttendance(String employeeId);

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

	String addBillableHours(BillableHoursDto billableHoursDto, String employeeId);

	String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId);

//	String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId);

//	ApplyLeaveDto getLeave(String employeeId);

	RegularizationHoursDto getRegularizationHours(String employeeId, LocalDate date);

	BillableHoursDto getBillableHours(String employeeId, LocalDate date);

	String addLeave(ApplyLeaveDto applyLeaveDto, String employeeId);

	ApplyLeaveDto getLeave(String employeeId);

	RegularizationHoursDto getRegularizationHours(String employeeId);

	BillableHoursDto getBillableHours(String employeeId);

	String getAttendanceAsJson(String managerId, String month);

	ManagerAttendanceEditDto editManagerAttendance(ManagerAttendanceEditDto managerAttendanceEditDto,
			String employeeId);

	String getDuration(String employeeId);
	/*
	 * <<<<<<< HEAD
	 * 
	 * List<Object[]> findAttendanceByManagerAndMonth(String managerId, String
	 * month);
	 * 
	 * =======
	 */

	List<ManagerAttendanceViewDto> findAttendanceByManagerAndMonth(String managerId, String month);

//	List<ManagerAttendanceViewDto> findAttendanceByManager(String managerId);

//>>>>>>> branch 'ramachandra' of https://github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git
	ManagerAttendanceEditDto getManagerAttendance(String employeeId, LocalDate date);

	RegularizationManagerEditDto editregularization(RegularizationManagerEditDto regularizationManagerEditDto,
			String employeeId);

	RegularizationManagerEditDto editregularizationreject(RegularizationManagerEditDto regularizationManagerEditDto,
			String employeeId);
//	List<Object[]> findAttendanceByManagerAndMonth(String managerId, String month);

	List<ManagerAttendanceDetailsDto> getAttendance(AttendanceRequestDto attendanceRequestDto);

	Set<UserAttendanceDto> allAttendance(String employeeId, Integer month, Integer year);

	List<AttendanceSummaryDto> getSummary(Integer month, Integer year);

	List<UserAttendanceSummaryDto> getSummary(String employeeId, Integer year);

//	ApplyLeaveDto addLeave(ApplyLeaveDto leaveDto);

//	float gettotalHoursFromBillableHoursDto(String employeeId);

//	LocalTime getinTimeFromAttendance(String employeeId);

//	LocalTime getoutTimeFromAttendance(String employeeId);

}
