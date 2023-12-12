package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.RegisterUserEntity;
import com.hrm.main.services.IRegisterUserService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping(value = "/RegisterUser")

public class RegisterUserController {
	@Autowired
	IRegisterUserService userServ;

	@PostMapping("/Register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUserEntity user) {
		String result = this.userServ.registerUser(user);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("/Authenticate")
	public ResponseEntity<String> authenticateUser(@RequestBody RegisterUserEntity request) {
		boolean isAuthenticated = userServ.authenticateUser(request);

		if (isAuthenticated) {
			return new ResponseEntity<String>("Authenticated", HttpStatus.OK);
		} else {
			return new ResponseEntity<String>(" Not Authenticated", HttpStatus.BAD_REQUEST);

		}
	}

	/*
	 * @GetMapping("/{email},{pass}") public RegisterUserEntity
	 * checkUser(@RequestParam String email, @RequestParam String pass) {
	 * this.userServ.checkUser(email, pass); return null; }
	 */
}
