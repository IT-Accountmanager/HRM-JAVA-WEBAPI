package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.PersonalDetails;
import com.hrm.main.services.IPersonalDetailsService;
@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

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

}
