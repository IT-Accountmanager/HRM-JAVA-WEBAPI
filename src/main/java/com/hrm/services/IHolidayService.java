package com.hrm.services;

import java.util.List;

import com.hrm.models.Holiday;
import com.hrm.payloads.EmployeesNameDto;

public interface IHolidayService {

	String add(List<Holiday> holiday);
	
	List<Holiday> getListOfHolidays();

}
