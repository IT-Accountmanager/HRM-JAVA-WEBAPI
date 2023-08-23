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

import com.hrm.main.models.Family;
import com.hrm.main.services.IFamilyService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/family")

public class FamilyController {
	@Autowired
	IFamilyService familyService;

	@PostMapping("/AddFamily")
	public ResponseEntity<String> createFamily(@RequestBody Family family) {

		String result = this.familyService.createFamily(family);

		return new ResponseEntity<String>(result, HttpStatus.OK);

	}

	@GetMapping("/getFamily")
	public ResponseEntity<List<Family>> getAllFamily() {
		List<Family> allFamily = this.familyService.getAllFamily();
		return new ResponseEntity<List<Family>>(allFamily, HttpStatus.OK);
	}

}