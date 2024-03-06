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

import com.hrm.models.SocialDetails;
import com.hrm.services.ISocialDetailsService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

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

	@GetMapping("/get/{id}")
	public ResponseEntity<SocialDetails> getSocialServiceById(@PathVariable Integer id) {
		SocialDetails result = this.socialDetailsService.getSocialServiceById(id);
		return new ResponseEntity<SocialDetails>(result, HttpStatus.OK);
	}

}
