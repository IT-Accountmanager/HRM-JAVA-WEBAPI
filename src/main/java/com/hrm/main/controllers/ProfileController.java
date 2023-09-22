
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

import com.hrm.main.models.Onboarding;
import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Profile;
import com.hrm.main.services.IProfileService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
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

	@GetMapping("/get")
	public ResponseEntity<List<Onboarding>> getOnboarding() {
		List<Onboarding> onboardings = this.profileService.getOnboardings();
		return new ResponseEntity<List<Onboarding>>(onboardings, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Onboarding> getOnboarding(@PathVariable Integer id) {
		Onboarding onboardingById = this.profileService.getOnboardingById(id);
		return new ResponseEntity<Onboarding>(onboardingById, HttpStatus.OK);
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
	
	
}
