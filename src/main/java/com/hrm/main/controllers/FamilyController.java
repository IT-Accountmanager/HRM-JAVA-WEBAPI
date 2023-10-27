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

import com.hrm.main.models.Family;
import com.hrm.main.payloads.FamilyStatusResponse;
import com.hrm.main.services.IFamilyService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/family")

public class FamilyController {
	@Autowired
	IFamilyService familyService;

	@PostMapping("/AddFamily/{candidateId}")
	public ResponseEntity<String> createFamily(@RequestBody Family family, @PathVariable long candidateId) {

		String result = this.familyService.createFamily(family, candidateId);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/getAllFamily/{candidateId}")
	public ResponseEntity<List<Family>> getAllFamilyByCandidateId(@PathVariable long candidateId) {
		List<Family> allFamily = this.familyService.getAllFamilyByCandidateId(candidateId);
		return new ResponseEntity<List<Family>>(allFamily, HttpStatus.OK);
	}

	@GetMapping("/getFamily/{id}")
	public Family getFamilyById(@PathVariable int id) {
		Family famDetails = familyService.getFamilyById(id);
		return famDetails;
	}

	@PutMapping("/updateFamily/{id}")
	public ResponseEntity<String> updateFamily(@RequestBody Family family, @PathVariable Integer id) {
		String result = this.familyService.updateFamily(family, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteFamily/{family_id}")
	public ResponseEntity<String> deleteFamily(@PathVariable Integer family_id) {
		String result = this.familyService.deleteFamily(family_id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/get/status/{candidateId}")
	public ResponseEntity<FamilyStatusResponse> getFamilyStatusByCandidateId(@PathVariable long candidateId) {
		FamilyStatusResponse status = this.familyService.getFamilyStatusByCandidateId(candidateId);
		return new ResponseEntity<FamilyStatusResponse>(status, HttpStatus.OK);
	}

}