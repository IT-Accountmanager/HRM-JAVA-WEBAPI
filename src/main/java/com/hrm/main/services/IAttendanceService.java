package com.hrm.main.services;

import java.time.LocalDate;
import java.util.List;
import com.hrm.main.models.Attendance;
import com.hrm.main.payloads.ApplyLeaveDto;
import com.hrm.main.payloads.AttendanceEmployeeDto;

public interface IAttendanceService {

//	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<Attendance> allAttendance(String employeeId);

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);
	
//	ApplyLeaveDto getLeave(String employeeId);

	ApplyLeaveDto createOrUpdateLeave(ApplyLeaveDto leaveDto);

	String clockIn(String employeeId, LocalDate date);

}
