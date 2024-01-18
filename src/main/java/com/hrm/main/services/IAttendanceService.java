package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Attendance;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<Attendance> allAttendance();

	Attendance getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

}
