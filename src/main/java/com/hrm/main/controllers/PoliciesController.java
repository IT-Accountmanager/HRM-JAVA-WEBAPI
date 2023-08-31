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
import com.hrm.main.models.Policies;
import com.hrm.main.services.IPoliciesService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/general/policies")
public class PoliciesController {

	@Autowired
	IPoliciesService policiesService;

	@PostMapping("/add")
	public ResponseEntity<String> addPolicies(@RequestBody Policies policies) {
		String result = this.policiesService.addPolicies(policies);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<List<Policies>> allPolicies() {
		List<Policies> allPolicies = this.policiesService.allPolicies();
		return new ResponseEntity<List<Policies>>(allPolicies, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<Policies> getPolicies(@PathVariable Integer id) {
		Policies policies = this.policiesService.getPolicies(id);
		return new ResponseEntity<Policies>(policies, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> addPolicies(@RequestBody Policies policies, @PathVariable Integer id) {
		String result = this.policiesService.updatePolicies(policies, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletePolicies(@PathVariable Integer id) {
		String result = this.policiesService.deletePolicies(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
