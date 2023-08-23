package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Education;
import com.hrm.main.services.IEducationService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/education")
public class EducationController {
	@Autowired
	IEducationService educationService;

	@PostMapping("/AddEducation")
	public ResponseEntity<String> createEducation(@RequestBody Education education) {
		String result = this.educationService.createEducation(education);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/getEducation")
	public ResponseEntity<List<Education>> getAllEducation() {
		List<Education> allEducation = this.educationService.getAllEducation();
		return new ResponseEntity<List<Education>>(allEducation, HttpStatus.OK);
	}

}
