package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Attendance;
import com.hrm.main.payloads.AttendanceEmployeeDto;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<Attendance> allAttendance();

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

}
