package com.hrm.services;

import com.hrm.models.EmployeeWorkWeek;
import com.hrm.payloads.EmployeeWorkWeekDto;

public interface EmployeeWorkWeekService {
	
//	EmployeeWorkWeek addWorkWeek(EmployeeWorkWeek employeeWorkWeek);
	
	String addWorkWeek(EmployeeWorkWeekDto employeeWorkWeekDto, String employeeId);
	
	EmployeeWorkWeekDto getWorkWeek(String employeeId);

}
