
package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.AddressDetails;
import com.hrm.main.services.IAddressDetailsService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController

@RequestMapping("/AddressDetails")
public class AddressDetailsController {

	@Autowired
	IAddressDetailsService addressDetailsService;

	@PostMapping("/AddAddress")
	public ResponseEntity<String> addAddress(@RequestBody AddressDetails addressDetails) {
		String result = addressDetailsService.addAdd(addressDetails);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
}
