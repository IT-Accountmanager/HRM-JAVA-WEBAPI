package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.SummaryDto;

public interface ISummaryService {

	List<SummaryDto> getAll();

	EmployeeViewDto getSummaryByCandidateId(long candidateId);

	String importEmployees(List<EmployeeViewDto> employees);

	List<EmployeesNameDto> getListOfEmployees();

}
