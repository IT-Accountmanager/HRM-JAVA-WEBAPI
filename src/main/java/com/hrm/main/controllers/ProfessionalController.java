package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.payloads.DirectReportsDto;
import com.hrm.main.payloads.ReportingManagerDto;
import com.hrm.main.payloads.ResignationEditDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.WorkHistoryDto;
import com.hrm.main.services.IProfessionalService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/professional")
public class ProfessionalController {

	@Autowired
	IProfessionalService professionalService;

	@GetMapping("workHistory/{employeeId}")
	public ResponseEntity<WorkHistoryDto> getWorkHistory(@PathVariable String employeeId) {
		WorkHistoryDto workHistory = this.professionalService.getWorkHistory(employeeId);
		return new ResponseEntity<WorkHistoryDto>(workHistory, HttpStatus.OK);
	}

	// ----------------------- Add Resignation Info-------------------
	@PostMapping("/resignation/edit/{employeeId}")
	public ResponseEntity<String> addResignationInfo(@RequestBody ResignationEditDto resignationEditDto,
			@PathVariable String employeeId) {
		String result = this.professionalService.addResignationInfo(resignationEditDto, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------------- Get Resignation Info-------------------
	@GetMapping("/resignation/get/{employeeId}")
	public ResponseEntity<ResignationInfoDto> getResignationInfo(@PathVariable String employeeId) {
		ResignationInfoDto result = this.professionalService.getResignationInfo(employeeId);
		return new ResponseEntity<ResignationInfoDto>(result, HttpStatus.OK);
	}

	// ----------------REPORTING MANAGER--------------------
	@GetMapping("get_reporting_manager/{employeeId}")
	public ResponseEntity<ReportingManagerDto> get(@PathVariable String employeeId) {
		ReportingManagerDto result = this.professionalService.getReportingManager(employeeId);
		return new ResponseEntity<ReportingManagerDto>(result, HttpStatus.OK);
	}

	@PostMapping("reporting_manager/{employeeId}")
	public String add(@RequestBody ReportingManagerDto reportingManagerDto, @PathVariable String employeeId) {
		String result = this.professionalService.addReportingManager(reportingManagerDto, employeeId);
		return result;
	}

	// --------------DIRECT REPORTS-------------------------
	@GetMapping("directReports/{employeeId}")
	public ResponseEntity<List<DirectReportsDto>> getDirectReoprts(@PathVariable String employeeId) {
		List<DirectReportsDto> list = this.professionalService.getDirectReports(employeeId);
		return new ResponseEntity<List<DirectReportsDto>>(list, HttpStatus.OK);

	}

}
