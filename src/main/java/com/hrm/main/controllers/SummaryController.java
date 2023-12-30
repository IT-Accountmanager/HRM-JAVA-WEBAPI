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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.payloads.BasicInfoDto;
import com.hrm.main.payloads.EmployeeGenerateDto;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.ResignationInfoDto;
import com.hrm.main.payloads.SetManagerDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.payloads.WorkHistoryDto;
import com.hrm.main.payloads.WorkInfoDto;
import com.hrm.main.services.ISummaryService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Summary")

public class SummaryController {

	@Autowired
	ISummaryService summaryService;

	// --------------------VIEW ALL EMPLOYEE IN SUMMARY TABLE---------------

	@GetMapping("/getAll")
	public ResponseEntity<List<SummaryDto>> getAllSummary() {

		List<SummaryDto> allSummary = this.summaryService.getAll();

		return new ResponseEntity<List<SummaryDto>>(allSummary, HttpStatus.OK);
	}

	// ----------------------Import-------------------------------
	@PostMapping("/import")
	public ResponseEntity<String> createSummary(@RequestBody List<EmployeeViewDto> employees) {

		String result = this.summaryService.importEmployees(employees);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	/*
	 * // ------------Search Name For Manager-----------------------
	 * 
	 * @GetMapping("/import/listOfEmployee") public
	 * ResponseEntity<List<EmployeesNameDto>> getListOfEmployees() {
	 * List<EmployeesNameDto> list = this.summaryService.getListOfEmployees();
	 * return new ResponseEntity<List<EmployeesNameDto>>(list, HttpStatus.OK); }
	 */

	// ------------Search Name For Manager-----------------------
	@GetMapping("/import/listOfEmployee")
	public ResponseEntity<List<EmployeesNameDto>> getListOfEmployees(
			@RequestParam(required = false) String searchTerm) {
		List<EmployeesNameDto> list;

		if (searchTerm != null && !searchTerm.isEmpty()) {
			list = this.summaryService.getListOfEmployeesBySearchTerm(searchTerm);
		} else {
			list = this.summaryService.getListOfEmployees();
		}

		return new ResponseEntity<>(list, HttpStatus.OK);
	}

	@PostMapping("/setManager/{employeeId}")
	public ResponseEntity<SetManagerDto> setManager(@RequestBody SetManagerDto assignTo,
			@PathVariable String employeeId) {
		SetManagerDto result = this.summaryService.setManager(assignTo, employeeId);
		return new ResponseEntity<SetManagerDto>(result, HttpStatus.OK);
	}

	@GetMapping("/getSummaryByCandidateId/{candidateId}")
	public ResponseEntity<EmployeeViewDto> getSummaryByCandidateId(@PathVariable long candidateId) {
		EmployeeViewDto result = summaryService.getSummaryByCandidateId(candidateId);
		return new ResponseEntity<EmployeeViewDto>(result, HttpStatus.OK);
	}

	/*
	 * @PutMapping("/updateSummary/{id}") public ResponseEntity<String>
	 * updateSummary(@RequestBody OnboardingEmployeeSummary summary,@PathVariable
	 * Integer id) { String result =
	 * this.onboardingEmployeeSummaryService.updateSummary(summary, id); return new
	 * ResponseEntity<>(result,HttpStatus.OK); }
	 * 
	 * @DeleteMapping("/deleteSummary/{id}") public ResponseEntity<String>
	 * deleteSummary(@PathVariable Integer id) { String result =
	 * this.onboardingEmployeeSummaryService.deleteSummary(id); return new
	 * ResponseEntity<>(result, HttpStatus.OK); }
	 */

	// ----------------------- Add Basic Info-------------------
	@PostMapping("/basicInfo/add/{employeeId}")
	public ResponseEntity<String> addBasicInfo(@RequestBody BasicInfoDto basicInfo, @PathVariable String employeeId) {
		String result = this.summaryService.addBasicInfo(basicInfo, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------------- Get Basic Info-------------------
	@GetMapping("/basicInfo/get/{employeeId}")
	public ResponseEntity<BasicInfoDto> getBasicInfo(@PathVariable String employeeId) {
		BasicInfoDto result = this.summaryService.getBasicInfo(employeeId);
		return new ResponseEntity<BasicInfoDto>(result, HttpStatus.OK);
	}

	// ----------------------- Add Work Info-------------------
	@PostMapping("/workInfo/add/{employeeId}")
	public ResponseEntity<String> addWorkInfo(@RequestBody WorkInfoDto workInfo, @PathVariable String employeeId) {
		String result = this.summaryService.addWorkInfo(workInfo, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------------- Get Work Info-------------------
	@GetMapping("/workInfo/get/{employeeId}")
	public ResponseEntity<WorkInfoDto> getWorkInfo(@PathVariable String employeeId) {
		WorkInfoDto result = this.summaryService.getWorkInfo(employeeId);
		return new ResponseEntity<WorkInfoDto>(result, HttpStatus.OK);
	}

	// ----------------------- Add History Info-------------------
	@PostMapping("/workHistory/add/{employeeId}")
	public ResponseEntity<String> addWorkHistory(@RequestBody WorkHistoryDto workHistory,
			@PathVariable String employeeId) {
		String result = this.summaryService.addWorkHistory(workHistory, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------------- Get Work History-------------------
	@GetMapping("/workHistory/get/{employeeId}")
	public ResponseEntity<WorkHistoryDto> getWorkHistory(@PathVariable String employeeId) {
		WorkHistoryDto result = this.summaryService.getWorkHistory(employeeId);
		return new ResponseEntity<WorkHistoryDto>(result, HttpStatus.OK);
	}

	// ----------------------- Add Resignation Info-------------------
	@PostMapping("/resignationInfo/add/{employeeId}")
	public ResponseEntity<String> addResignationInfo(@RequestBody ResignationInfoDto resignationInfo,
			@PathVariable String employeeId) {
		String result = this.summaryService.addResignationInfo(resignationInfo, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------------- Get Resignation Info-------------------
	@GetMapping("/resignationInfo/get/{employeeId}")
	public ResponseEntity<ResignationInfoDto> getResignationInfo(@PathVariable String employeeId) {
		ResignationInfoDto result = this.summaryService.getResignationInfo(employeeId);
		return new ResponseEntity<ResignationInfoDto>(result, HttpStatus.OK);
	}

	// ----------------------Set Employee Status----------------------
	@PostMapping("employeeStatus/{employeeId}")
	public ResponseEntity<String> changeEmployeeStatus(@PathVariable String employeeId,
			@RequestBody EmployeeGenerateDto status) {
		String result = this.summaryService.changeEmployeeStatus(employeeId, status);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// -----------------Employee Status List---------------------------------
	@GetMapping("/get/employeeStatusList")
	public EmployeeStatus[] getEmployeeStatusList() {
		EmployeeStatus[] status = EmployeeStatus.values();
		return status;
	}

}
