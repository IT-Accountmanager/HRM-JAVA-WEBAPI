package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Attendance;

public interface IAttendanceService {

	String addAttendence(Attendance attendance);

	List<Attendance> allAttendance();

	Attendance getAttendance(Integer id);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

}
