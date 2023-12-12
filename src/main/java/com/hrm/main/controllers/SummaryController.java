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
import com.hrm.main.models.OnboardingEmployeeSummary;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.payloads.EmployeeViewDto;
import com.hrm.main.payloads.EmployeesNameDto;
import com.hrm.main.payloads.SummaryDto;
import com.hrm.main.services.IOnboardingEmployeeSummaryService;
import com.hrm.main.services.ISummaryService;
import com.hrm.main.servicesImpls.OnboardingEmployeeSummaryServiceImpl;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Summary")

public class SummaryController {

	@Autowired
	ISummaryService summaryService;
	@Autowired
	IOnboardingEmployeeSummaryService onboardingEmployeeSummaryService;

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

	// ------------Search Name For Manager-----------------------
	@GetMapping("/import/listOfEmployee")
	public ResponseEntity<List<EmployeesNameDto>> getListOfEmployees() {
		List<EmployeesNameDto> list = this.summaryService.getListOfEmployees();
		return new ResponseEntity<List<EmployeesNameDto>>(list, HttpStatus.OK);
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

	@GetMapping("/getDepartments")
	public ResponseEntity<List<String>> findDepartments() {
		List<String> findDepartments = this.onboardingEmployeeSummaryService.findDepartments();
		return new ResponseEntity<List<String>>(findDepartments, HttpStatus.OK);
	}

	@GetMapping("/getHrEmployee")
	public ResponseEntity<List<String>> findHrEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findHREmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getFinanceEmployee")
	public ResponseEntity<List<String>> findFinanceEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findFinanceEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getDevelopmentEmployee")
	public ResponseEntity<List<String>> findDevelopmentEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findDevelopmentEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getMGMTEmployee")
	public ResponseEntity<List<String>> findMGMTEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findMGMTEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getIASEmployee")
	public ResponseEntity<List<String>> findIASEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findIASEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getDFSEmployee")
	public ResponseEntity<List<String>> findDFSEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findDFSEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getITEmployee")
	public ResponseEntity<List<String>> findITEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findITEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getBIMEmployee")
	public ResponseEntity<List<String>> findBIMEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findBIMEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getEDSEmployee")
	public ResponseEntity<List<String>> findEDSEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findEDSEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getSystemAdminEmployee")
	public ResponseEntity<List<String>> findSystemAdminEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findSystemAdminEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

	@GetMapping("/getSalesEmployee")
	public ResponseEntity<List<String>> findSalesEmployee() {
		List<String> employee = this.onboardingEmployeeSummaryService.findSalesEmployee();
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

}
