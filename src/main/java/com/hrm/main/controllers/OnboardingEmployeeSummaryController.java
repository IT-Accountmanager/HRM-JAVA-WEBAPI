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
import com.hrm.main.services.IOnboardingEmployeeSummaryService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Summary")

public class OnboardingEmployeeSummaryController {

	@Autowired
	IOnboardingEmployeeSummaryService onboardingEmployeeSummaryService;

	@PostMapping("/AddSummary")
	public ResponseEntity<String> createSummary(@RequestBody OnboardingEmployeeSummary summary) {

		String result = this.onboardingEmployeeSummaryService.createSummary(summary);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/getAllSummary")
	public ResponseEntity<List<OnboardingEmployeeSummary>> getAllSummary() {

		List<OnboardingEmployeeSummary> allSummary = this.onboardingEmployeeSummaryService.getAllSummary();

		return new ResponseEntity<List<OnboardingEmployeeSummary>>(allSummary, HttpStatus.OK);
	}

	@GetMapping("/getSummaryById/{id}")
	public OnboardingEmployeeSummary getSummaryById(@PathVariable int id) {
		OnboardingEmployeeSummary summary = onboardingEmployeeSummaryService.getSummaryById(id);
		return summary;
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
}
