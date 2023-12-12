package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.services.IOnboardingService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Onboarding")

public class OnboardingController {

	@Autowired
	IOnboardingService onboardingService;

	/*
	 * @PostMapping("/post") public ResponseEntity<String>
	 * addOnboarding(@RequestBody Onboarding onboarding) { String createOnboarding =
	 * this.onboardingService.createOnboarding(onboarding); return new
	 * ResponseEntity<String>(createOnboarding, HttpStatus.CREATED); }
	 */

	@PostMapping("/post")
	public ResponseEntity<Onboarding> addOnboarding(@RequestBody Onboarding onboardingRequest) {

		Onboarding createOnboarding = this.onboardingService.createOnboarding(onboardingRequest);
		return new ResponseEntity<Onboarding>(createOnboarding, HttpStatus.OK);
	}

	@PostMapping("/post/import")
	public ResponseEntity<String> addOnboarding(@RequestBody List<Onboarding> onboardings) {
		String result = this.onboardingService.createOnboarding(onboardings);
		return new ResponseEntity<String>(result, HttpStatus.CREATED);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Onboarding>> getAllOnboarding() {
		List<Onboarding> allOnboarding = this.onboardingService.getAllOnboarding();
		return new ResponseEntity<List<Onboarding>>(allOnboarding, HttpStatus.OK);
	}

	@GetMapping("/get/{candidateId}")
	public ResponseEntity<Onboarding> getOnboarding(@PathVariable long candidateId) {
		Onboarding onboarding = this.onboardingService.getOnboardingByCandidateId(candidateId);
		return new ResponseEntity<Onboarding>(onboarding, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> getOnboarding(@RequestBody Onboarding onboarding, @PathVariable Integer id) {
		String updateOnboarding = this.onboardingService.updateOnboarding(onboarding, id);
		return new ResponseEntity<String>(updateOnboarding, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteOnboarding(@PathVariable Integer id) {
		String deleteOnboarding = this.onboardingService.deleteOnboarding(id);
		return new ResponseEntity<String>(deleteOnboarding, HttpStatus.OK);
	}

	@GetMapping("/getSrNo")
	public Long getNextSerialNumberForAdd() {
		return this.onboardingService.nextValue() + 1;

	}

	@GetMapping("/departments")
	public String[] getDepartments() {
		Departments[] departments = Departments.values();
		return formatDepartments(departments);
	}

	private String[] formatDepartments(Departments[] departments) {
		String[] formattedDepartments = new String[departments.length];

		for (int i = 0; i < departments.length; i++) {
			formattedDepartments[i] = formatDepartmentName(departments[i].name());
		}

		return formattedDepartments;
	}

	private String formatDepartmentName(String departmentName) {
		// Replace camel case with spaces and capitalize each word
		return departmentName.replaceAll(String.format("%s|%s|%s", "(?<=[A-Z])(?=[A-Z][a-z])", "(?<=[^A-Z])(?=[A-Z])",
				"(?<=[A-Za-z])(?=[^A-Za-z])"), " ");
	}
}
