package com.hrm.main.controllers;

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

import com.hrm.main.models.PersonalDetails;
import com.hrm.main.models.Helper.EnumCollection.BloodGroup;
import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.models.Helper.EnumCollection.EmployeeStatus;
import com.hrm.main.services.IPersonalDetailsService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/PersonalDetails")
public class PersonalDetailsController {

	@Autowired
	IPersonalDetailsService personalDetailsService;

	@PostMapping("/add")
	public ResponseEntity<String> addPersonalDetails(@RequestBody PersonalDetails personalDetails) {

		String detailsAdded = personalDetailsService.addPersonalDetails(personalDetails);
		return new ResponseEntity<String>(detailsAdded, HttpStatus.OK);
	}

	@GetMapping("/bloodGroup")
	public BloodGroup[] getBloodGroup() {
		return BloodGroup.values();
	}

}
