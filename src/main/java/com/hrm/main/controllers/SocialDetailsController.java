package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.SocialDetails;
import com.hrm.main.services.ISocialDetailsService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/SocialDetails")
public class SocialDetailsController {

	@Autowired
	ISocialDetailsService socialDetailsService;

	@PostMapping("/add")
	public ResponseEntity<String> addSocialDetails(@RequestBody SocialDetails socialDetails) {
		String result = this.socialDetailsService.addSocialDetails(socialDetails);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
