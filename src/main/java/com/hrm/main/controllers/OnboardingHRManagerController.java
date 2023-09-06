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

import com.hrm.main.models.OnboardingHRManager;
import com.hrm.main.services.IOnboardingHRManagerService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/HRManager")

public class OnboardingHRManagerController {

	@Autowired
	IOnboardingHRManagerService onboardingHRManagerService;

	@PostMapping("/AddManager")
	public ResponseEntity<String> createHRManager(@RequestBody OnboardingHRManager hrManager) {

		String result = this.onboardingHRManagerService.createHRManager(hrManager);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/getAllManager")
	public ResponseEntity<List<OnboardingHRManager>> getAllManager() {

		List<OnboardingHRManager> allmanager = this.onboardingHRManagerService.getAllHRManager();

		return new ResponseEntity<List<OnboardingHRManager>>(allmanager, HttpStatus.OK);
	}

	@GetMapping("/getManagerById/{id}")
	public OnboardingHRManager getManagerById(@PathVariable int id) {

		OnboardingHRManager hr = onboardingHRManagerService.getHRManager(id);

		return hr;
	}

	@PutMapping("/updateManager/{id}")
	public ResponseEntity<String> updateHRManager(@RequestBody OnboardingHRManager hrManager,
			@PathVariable Integer id) {

		String result = this.onboardingHRManagerService.updateHRManager(hrManager, id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteManager/{id}")
	public ResponseEntity<String> deleteHRManager(@PathVariable Integer id) {

		String result = this.onboardingHRManagerService.deleteHRManager(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/getSrNo")
	public Long getNextSerialNumberForAdd() {
		return this.onboardingHRManagerService.nextValue() + 1;

	}

}
