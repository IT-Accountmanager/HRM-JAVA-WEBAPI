package com.hrm.controllers;

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

import com.hrm.helper.EnumCollection.BloodGroup;
import com.hrm.helper.EnumCollection.Departments;
import com.hrm.helper.EnumCollection.EmployeeStatus;
import com.hrm.models.PersonalDetails;
import com.hrm.services.IPersonalDetailsService;

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
