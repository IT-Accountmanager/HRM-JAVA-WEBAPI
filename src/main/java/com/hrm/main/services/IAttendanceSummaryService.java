package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.AttendanceSummary;

public interface IAttendanceSummaryService {

	String addAttendence(AttendanceSummary attendance);

	List<AttendanceSummary> allAttendance();

	AttendanceSummary getAttendance(Integer id);

	String deleteAttendance(Integer id);

	String editAttendance(AttendanceSummary attendance, Integer id);

}
