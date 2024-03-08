package com.hrm.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
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

import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.Designation;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.helper.EnumCollection.ManagerType;
import com.hrm.helper.EnumCollection.ProbationPeriod;
import com.hrm.helper.EnumCollection.WorkLocation;
import com.hrm.helper.EnumCollection.Departments.Department;
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
import com.hrm.services.ISummaryService;

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

	/*
	 * // ----------------------- Get Work History-------------------
	 * 
	 * @GetMapping("/workHistory/get/{employeeId}") public
	 * ResponseEntity<WorkHistoryDto> getWorkHistory(@PathVariable String
	 * employeeId) { WorkHistoryDto result =
	 * this.summaryService.getWorkHistory(employeeId); return new
	 * ResponseEntity<WorkHistoryDto>(result, HttpStatus.OK); }
	 */

	// ----------------------Set Employee Status----------------------
	@PostMapping("employeeStatus/{employeeId}")
	public ResponseEntity<String> changeEmployeeStatus(@PathVariable String employeeId,
			@RequestBody EmployeeGenerateDto status) {
		String result = this.summaryService.changeEmployeeStatus(employeeId, status);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// -----------------Employee Status List---------------------------------
	@GetMapping("/get/employeeStatusList")
	public List<String> getEmployeeStatusList() {
		EmployeeStatus[] statusArray = EmployeeStatus.values();

		List<String> employeeStatusList = Arrays.stream(statusArray).map(status -> status.name().replace("_", " "))
				.collect(Collectors.toList());
		return employeeStatusList;
	}

	// ----------------Personal Info----------------
	@GetMapping("getPersonalInfo/{employeeId}")
	public ResponseEntity<SummaryPersonalInfoDto> getPersonalInfo(@PathVariable String employeeId) {
		SummaryPersonalInfoDto result = this.summaryService.getPersonalInfo(employeeId);
		return new ResponseEntity<SummaryPersonalInfoDto>(result, HttpStatus.OK);
	}

	// ----------------Contact Info----------------
	@GetMapping("getContactInfo/{employeeId}")
	public ResponseEntity<SummaryContactInfoDto> getContactInfo(@PathVariable String employeeId) {
		SummaryContactInfoDto result = this.summaryService.getContactInfo(employeeId);
		return new ResponseEntity<SummaryContactInfoDto>(result, HttpStatus.OK);
	}

	// ----------------Address Info----------------
	@GetMapping("getAddressInfo/{employeeId}")
	public ResponseEntity<SummaryAddressInfoDto> getAddressInfo(@PathVariable String employeeId) {
		SummaryAddressInfoDto result = this.summaryService.getAddressInfo(employeeId);
		return new ResponseEntity<SummaryAddressInfoDto>(result, HttpStatus.OK);
	}

	@GetMapping("department")
	public List<String> getAllDepartments() {
		Departments[] departments = Departments.values();
		List<String> departmentList = Arrays.stream(departments).map(department -> department.name().replace("_", " "))
				.collect(Collectors.toList());
		return departmentList;
	}

	@GetMapping("subdepartment/{department}")
	public List<String> getSubdepartments(@PathVariable String department) {

		Departments requiredDepartment = Departments.valueOf(department.replace(" ", "_"));
		Department[] subdepartments = requiredDepartment.getSubdepartments();
		List<String> subDepartmentList = Arrays.stream(subdepartments)
				.map(subdepartment -> subdepartment.name().replace("_", " ")).collect(Collectors.toList());
		return subDepartmentList;
	}

	@GetMapping("probation_period_list")
	public List<String> getProbationPeriodList() {
		ProbationPeriod[] periods = ProbationPeriod.values();
		List<String> periodList = Arrays.stream(periods).map(period -> period.name().replace("_", " "))
				.collect(Collectors.toList());
		return periodList;
	}

	@GetMapping("work_location_list")
	public List<String> getWorkLocationList() {
		WorkLocation[] locations = WorkLocation.values();
		List<String> locationsList = Arrays.stream(locations).map(location -> location.name().replace("_", " "))
				.collect(Collectors.toList());
		return locationsList;
	}

	@GetMapping("designation_list")
	public List<String> getDesignationList() {
		Designation[] designations = Designation.values();
		List<String> designationsList = Arrays.stream(designations)
				.map(designation -> designation.name().replace("_", " ")).collect(Collectors.toList());
		return designationsList;
	}

	@GetMapping("manager_type_list")
	public ManagerType[] getManagerTypeList() {
		return ManagerType.values();
	}
	

}
