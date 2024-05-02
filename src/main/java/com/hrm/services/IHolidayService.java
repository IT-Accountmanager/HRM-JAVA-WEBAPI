package com.hrm.services;

import java.util.List;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.hrm.models.Holiday;
import com.hrm.payloads.EmployeesNameDto;

public interface IHolidayService {

	String add(ArrayNode holiday);

	List<Holiday> getListOfHolidays();

}
