package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Personal;
import com.hrm.main.services.IPersonalService;

@RestController
@RequestMapping("/Personal")
public class PersonalController {
	@Autowired
	IPersonalService personalService;

	@PostMapping("/add")
	public ResponseEntity<String> addPersonal(@RequestBody Personal personal) {
		String result = this.personalService.addPersonal(personal);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
