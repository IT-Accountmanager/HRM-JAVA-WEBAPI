package com.hrm.controllers;

import java.util.Base64;
import java.util.Base64.Decoder;

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

import com.hrm.helper.EnumCollection;
import com.hrm.helper.EnumCollection.DetailsSubmissionStatus;
import com.hrm.models.Personal;
import com.hrm.models.PersonalDetails;
import com.hrm.payloads.PersonalStatusResponse;
import com.hrm.services.IPersonalService;

//@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/Personal")
public class PersonalController {
	@Autowired
	IPersonalService personalService;

	@PostMapping("/add/{candidateId}")
	public ResponseEntity<String> addPersonal(@RequestBody Personal personal, @PathVariable long candidateId) {

		String result = this.personalService.addPersonal(personal, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// Get personal details by candidate id

	@GetMapping("/get/{candidateId}")
	public ResponseEntity<Personal> getPersonalDetailsByCandidateId(@PathVariable long candidateId) {
		Personal result = this.personalService.getPersonalDetailsByCandidateId(candidateId);
		return new ResponseEntity<Personal>(result, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/get/{id}") public ResponseEntity<Personal>
	 * getPersonalById(@PathVariable Integer id) { Personal result =
	 * this.personalService.getPersonalById(id); return new
	 * ResponseEntity<Personal>(result, HttpStatus.OK); }
	 */

	/*
	 * @GetMapping("/get/status/{candidateId}") public ResponseEntity<Personal>
	 * getStatusByCndidateId(@PathVariable String candidateId) { Personal
	 * statusByCandidateId =
	 * this.personalService.getStatusByCandidateId(candidateId); return new
	 * ResponseEntity<Personal>(statusByCandidateId, HttpStatus.OK); }
	 */

	@GetMapping("/get/status/{candidateId}")
	public ResponseEntity<PersonalStatusResponse> getStatusByCandidateId(@PathVariable long candidateId) {
		PersonalStatusResponse statusResponse = this.personalService.getStatusByCandidateId(candidateId);
		return new ResponseEntity<PersonalStatusResponse>(statusResponse, HttpStatus.OK);

	}

}
