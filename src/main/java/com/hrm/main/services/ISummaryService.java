package com.hrm.main.services;

import java.util.List;

import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.payloads.BasicInfoDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.ReportingManagerDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.SetManagerDto;
import com.hrm.main.payloads.SummaryAddressInfoDto;
import com.hrm.main.payloads.SummaryContactInfoDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.payloads.SummaryPersonalInfoDto;
import com.hrm.main.payloads.WorkHistoryDto;
import com.hrm.main.payloads.WorkInfoDto;

public interface ISummaryService {

	List<SummaryDto> getAll();

	EmployeeViewDto getSummaryByCandidateId(long candidateId);

	String importEmployees(List<EmployeeViewDto> employees);

	List<EmployeesNameDto> getListOfEmployees();

	List<EmployeesNameDto> getListOfEmployeesBySearchTerm(String searchTerm);

	SetManagerDto setManager(SetManagerDto assignTo, String employeeId);

	String addBasicInfo(BasicInfoDto basicInfo, String employeeId);

	String addWorkInfo(WorkInfoDto workInfo, String employeeId);

	String addWorkHistory(WorkHistoryDto workHistory, String employeeId);

	String addResignationInfo(ResignationInfoDto resignationInfo, String employeeId);

	WorkInfoDto getWorkInfo(String employeeId);

	BasicInfoDto getBasicInfo(String employeeId);

	WorkHistoryDto getWorkHistory(String employeeId);

	ResignationInfoDto getResignationInfo(String employeeId);

	String changeEmployeeStatus(String employeeId, EmployeeGenerateDto status);

	SummaryPersonalInfoDto getPersonalInfo(String employeeId);

	SummaryContactInfoDto getContactInfo(String employeeId);

	SummaryAddressInfoDto getAddressInfo(String employeeId);

	String addReportingManager(ReportingManagerDto reportingManagerDto, String employeeId);

	ReportingManagerDto getReportingManager(String employeeId);

}
