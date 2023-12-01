package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Agreement;
import com.hrm.main.payloads.AgreementDto;
import com.hrm.main.services.IAgreementService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/agreement")
public class AgreementController {

	@Autowired
	IAgreementService agreementService;

	// ------------ Add Data ---------------
	@PostMapping("/add/{candidateId}")
	public ResponseEntity<String> add(@RequestBody Agreement agreement, @PathVariable long candidateId) {
		String result = this.agreementService.add(candidateId, agreement);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ------------- Delete Agreement ----------------

	/*
	 * @DeleteMapping("/delete/{EmployeeId}") public ResponseEntity<String>
	 * delete(@PathVariable String employeeId) { String result =
	 * this.agreementService.delete(employeeId); return new
	 * ResponseEntity<String>(result, HttpStatus.OK); }
	 */
	// --------------Get By Employee Id -----------------

	@GetMapping("/getAgreement/{candidateId}")
	public ResponseEntity<AgreementDto> getByCandidateId(@PathVariable long candidateId) {
		AgreementDto result = this.agreementService.getByCandidateId(candidateId);
		return new ResponseEntity<AgreementDto>(result, HttpStatus.OK);
	}

}
