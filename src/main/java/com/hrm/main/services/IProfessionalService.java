package com.hrm.main.services;

import java.util.List;

import com.hrm.main.payloads.DirectReportsDto;
import com.hrm.main.payloads.ReportingManagerDto;
import com.hrm.main.payloads.ResignationEditDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.WorkHistoryDto;

public interface IProfessionalService {

	WorkHistoryDto getWorkHistory(String employeeId);

	String addResignationInfo(ResignationEditDto resignationEditDto, String employeeId);

	ResignationInfoDto getResignationInfo(String employeeId);

	List<ReportingManagerDto> getReportingManager(String employeeId);

	String addReportingManager(ReportingManagerDto reportingManagerDto, String employeeId);

	List<DirectReportsDto> getDirectReports(String employeeId);

}
