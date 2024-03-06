
package com.hrm.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.Helper.EnumCollection;
import com.hrm.Helper.EnumCollection.CandidatesStatus;
import com.hrm.Helper.EnumCollection.HrSubmission;
import com.hrm.models.Onboarding;
import com.hrm.models.PersonalDetails;
import com.hrm.models.Profile;
import com.hrm.payloads.ProfileSummaryDto;
import com.hrm.services.IProfileService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Profile")
public class ProfileController {

	@Autowired
	IProfileService profileService;

	/*
	 * @GetMapping("/get/{id}/{status}") public ResponseEntity<String>
	 * changeStatus(@PathVariable Integer id, @PathVariable String status) { String
	 * result = this.profileService.changeStatus(id, status); return new
	 * ResponseEntity<String>(result, HttpStatus.OK); }
	 */

	@GetMapping("/getAll")
	public ResponseEntity<List<Onboarding>> getPendingOnboarding() {
		List<Onboarding> onboardings = this.profileService.getPendingOnboardings();
		return new ResponseEntity<List<Onboarding>>(onboardings, HttpStatus.OK);
	}

	@GetMapping("/get/{candidateId}")
	public ResponseEntity<Onboarding> getOnboarding(@PathVariable long candidateId) {
		Onboarding onboarding = this.profileService.getOnboardingByCandidateId(candidateId);
		return new ResponseEntity<Onboarding>(onboarding, HttpStatus.OK);
	}

	@PostMapping("/post")
	public ResponseEntity<Integer> postProfile(@RequestBody Profile profile) {
		int result = this.profileService.createProfile(profile);
		return new ResponseEntity<Integer>(result, HttpStatus.CREATED);
	}

	/*
	 * @PostMapping("/post/personal") public ResponseEntity<Integer>
	 * postProfile(@RequestBody PersonalDetails personalDetails) { int result =
	 * this.profileService.createPersonalDetails(personalDetails); return new
	 * ResponseEntity<Integer>(result, HttpStatus.CREATED); }
	 */

	// ____ Update Onboarding Status if Profile of Candidate is Submitted ____
	@PutMapping("/update/status/{candidateId}")
	public ResponseEntity<Integer> updatingCandidatesStatus(@PathVariable long candidateId) {
		int result = this.profileService.updatingCandidatesStatus(candidateId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
