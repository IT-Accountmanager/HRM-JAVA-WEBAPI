package com.hrm.services;

import java.util.List;

import com.hrm.payloads.DirectReportsDto;
import com.hrm.payloads.MResignationEditDto;
import com.hrm.payloads.ReportingManagerDto;
import com.hrm.payloads.ResignationInfoDto;
import com.hrm.payloads.UResignationEditDto;
import com.hrm.payloads.WorkHistoryDto;

public interface IProfessionalService {

	WorkHistoryDto getWorkHistory(String employeeId);

	String addResignationInfo(MResignationEditDto resignationEditDto, String employeeId);

	ResignationInfoDto getResignationInfo(String employeeId);

	List<ReportingManagerDto> getReportingManager(String employeeId);

	String addReportingManager(ReportingManagerDto reportingManagerDto, String employeeId);

	List<DirectReportsDto> getDirectReports(String employeeId);

	String addUserResignationInfo(UResignationEditDto resignationEditDto, String employeeId);

}
