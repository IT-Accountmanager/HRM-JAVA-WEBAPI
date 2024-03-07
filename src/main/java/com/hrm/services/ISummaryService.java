package com.hrm.services;

import java.util.List;

import com.hrm.helper.EnumCollection.CandidatesStatus;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.payloads.BasicInfoDto;
import com.hrm.payloads.EmployeeGenerateDto;
import com.hrm.payloads.EmployeeViewDto;
import com.hrm.payloads.EmployeesNameDto;
import com.hrm.payloads.ReportingManagerDto;
import com.hrm.payloads.ResignationInfoDto;
import com.hrm.payloads.SetManagerDto;
import com.hrm.payloads.SummaryAddressInfoDto;
import com.hrm.payloads.SummaryContactInfoDto;
import com.hrm.payloads.SummaryDto;
import com.hrm.payloads.SummaryPersonalInfoDto;
import com.hrm.payloads.WorkHistoryDto;
import com.hrm.payloads.WorkInfoDto;

public interface ISummaryService {

	List<SummaryDto> getAll();

	EmployeeViewDto getSummaryByCandidateId(long candidateId);

	String importEmployees(List<EmployeeViewDto> employees);

	List<EmployeesNameDto> getListOfEmployees();

	List<EmployeesNameDto> getListOfEmployeesBySearchTerm(String searchTerm);

	SetManagerDto setManager(SetManagerDto assignTo, String employeeId);

	String editBasicInfo(BasicInfoDto basicInfo, String employeeId);

	String addWorkInfo(WorkInfoDto workInfo, String employeeId);

	String addWorkHistory(WorkHistoryDto workHistory, String employeeId);

	WorkInfoDto getWorkInfo(String employeeId);

	BasicInfoDto getBasicInfo(String employeeId);

	String changeEmployeeStatus(String employeeId, EmployeeGenerateDto status);

	SummaryPersonalInfoDto getPersonalInfo(String employeeId);

	SummaryContactInfoDto getContactInfo(String employeeId);

	SummaryAddressInfoDto getAddressInfo(String employeeId);

}
