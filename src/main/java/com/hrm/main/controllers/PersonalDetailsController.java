package com.hrm.main.controllers;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	@PostMapping("/upload")
	public ResponseEntity<String> uploadImage(
		@RequestParam("firstName")String firstName,
		@RequestParam("middleName")String MiddleName,
		@RequestParam("lastName")String latName,
		@RequestParam("dateOfBirth")String dateOfBirth,
		@RequestParam("gender")String gender,
		@RequestParam("bloodGroup")String bloodGroup,
		@RequestParam("maritalStatus")String maritalStatus,
		@RequestParam("officialMailId")String officialMailId,
		@RequestParam("imageData") MultipartFile imageData){
		
		try { personalDetailsService.uploadImage(firstName, MiddleName, firstName, dateOfBirth, gender, bloodGroup, maritalStatus, officialMailId, imageData.getBytes());
			return ResponseEntity.ok("personal details and Image uploaded succesfully");
		}
		catch (IOException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload Failed");
		}
	}
	
	
	

}
