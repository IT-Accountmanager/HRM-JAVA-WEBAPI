package com.hrm.main.services;

import com.hrm.main.payloads.ReportingManagerDto;
import com.hrm.main.payloads.ResignationEditDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.WorkHistoryDto;

public interface IProfessionalService {

	WorkHistoryDto getWorkHistory(String employeeId);

	String addResignationInfo(ResignationEditDto resignationEditDto, String employeeId);

	ResignationInfoDto getResignationInfo(String employeeId);

	ReportingManagerDto getReportingManager(String employeeId);

	String addReportingManager(ReportingManagerDto reportingManagerDto, String employeeId);

}
