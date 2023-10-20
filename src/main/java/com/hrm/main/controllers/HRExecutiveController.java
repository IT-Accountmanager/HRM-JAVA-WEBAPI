package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Onboarding.CandidatesStatus;
import com.hrm.main.models.Personal;
import com.hrm.main.services.IHRExecutiveService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/HRExecutive")

public class HRExecutiveController {

	@Autowired
	IHRExecutiveService hRExecutiveService;

	@PostMapping("/AddExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody HRExecutive executive) {

		String result = this.hRExecutiveService.createExecutive(executive);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/getAllExecutive")
	public ResponseEntity<List<HRExecutive>> getAllExecutive() {

		List<HRExecutive> allExecutive = this.hRExecutiveService.getAllExecutive();

		return new ResponseEntity<List<HRExecutive>>(allExecutive, HttpStatus.OK);
	}

	@GetMapping("/getExecutveById/{id}")
	public HRExecutive getExecutiveById(@PathVariable int id) {

		HRExecutive hr = hRExecutiveService.getExecutiveById(id);

		return hr;
	}

	@PutMapping("/updateExecutive/{id}")
	public ResponseEntity<String> updateHRExecutive(@RequestBody HRExecutive executive, @PathVariable Integer id) {

		String result = this.hRExecutiveService.updateHRExecutive(executive, id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteExecutive/{id}")
	public ResponseEntity<String> deleteHrExecutive(@PathVariable Integer id) {

		String result = this.hRExecutiveService.deleteHrExecutive(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 1. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @PostMapping("/transfer") public ResponseEntity<String> checkAndTranfer() {
	 * String personal = this.hRExecutiveService.tranferProfileToHRExecutive();
	 * return new ResponseEntity<String>(personal, HttpStatus.OK); }
	 */

	// 2. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @GetMapping("/getAllCandidate") // @Transactional public List<Onboarding>
	 * getAllOnboarding() {
	 * 
	 * List<Onboarding> result = this.hRExecutiveService.getAllOnboarding(); return
	 * result; }
	 */

	// 3. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @GetMapping("/getCandidates/{status}") public
	 * ResponseEntity<List<Onboarding>> candidates(@PathVariable Status status) {
	 * List<Onboarding> allOnboarding =
	 * this.hRExecutiveService.getAllOnboarding(status); return new
	 * ResponseEntity<List<Onboarding>>(allOnboarding, HttpStatus.OK); }
	 */

	@PostMapping("/postToHrExecutive/{status}")
	public ResponseEntity<Boolean> candidatesInHr(@PathVariable CandidatesStatus status) {
		boolean result = this.hRExecutiveService.postCandidateInHrExecutive(status);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

}
