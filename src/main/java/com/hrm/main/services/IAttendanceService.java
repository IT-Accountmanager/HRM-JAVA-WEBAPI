package com.hrm.main.services;

import java.util.List;
import com.hrm.main.models.Attendance;
import com.hrm.main.payloads.AttendanceEmployeeDto;
import com.hrm.main.payloads.BillableHoursDto;
import com.hrm.main.payloads.RegularizationHoursDto;

public interface IAttendanceService {

	String clockIn(String employeeId);

	String clockOut(String employeeId);

	List<Attendance> allAttendance(String employeeId);

	AttendanceEmployeeDto getAttendance(String employeeId);

	String deleteAttendance(Integer id);

	String editAttendance(Attendance attendance, Integer id);

	String addBillableHours(BillableHoursDto billableHoursDto, String employeeId);

	String addRegularizationHours(RegularizationHoursDto regularizationHoursDto, String employeeId);

}
