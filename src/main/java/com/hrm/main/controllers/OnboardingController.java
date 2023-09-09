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
import com.hrm.main.services.IOnboardingService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
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

	@GetMapping("/get/{id}")
	public ResponseEntity<Onboarding> getOnboarding(@PathVariable Integer id) {
		Onboarding onboardingById = this.onboardingService.getOnboardingById(id);
		return new ResponseEntity<Onboarding>(onboardingById, HttpStatus.OK);
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

}
