package com.hrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.models.BankDetails;
import com.hrm.services.IBankDetailsService;
@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/BankDetails")
public class BankDetailsController {

	@Autowired
	IBankDetailsService bankDetailsService;

	@PostMapping("/add")
	public ResponseEntity<String> addBankDetails(@RequestBody BankDetails bankDetails) {
		String result = bankDetailsService.addDetails(bankDetails);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
