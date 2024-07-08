package com.hrm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.models.RegisterUserEntity;
import com.hrm.payloads.AuthenticationResponse;
import com.hrm.repositories.IRegisterUserRepository;
import com.hrm.services.IRegisterUserService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping(value = "/RegisterUser")

public class RegisterUserController {
	@Autowired
	IRegisterUserRepository registerUserRepository;
	@Autowired
	IRegisterUserService userServ;

	private static final Logger logger = LoggerFactory.getLogger(RegisterUserController.class);

	@PostMapping("/Register")
	public ResponseEntity<String> registerUser(@RequestBody RegisterUserEntity user) {
		String result = this.userServ.registerUser(user);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@PostMapping("/Authenticate")
	public ResponseEntity<AuthenticationResponse> authenticateUser(@RequestBody RegisterUserEntity request) {
		RegisterUserEntity user = userServ.authenticateUser(request);
		String username = "";
		boolean isAuthenticated = user != null;
		if (isAuthenticated) {
			logger.info("Is Authenticated : {}", isAuthenticated);
			username = user.getUserName();
			AuthenticationResponse response = new AuthenticationResponse(username, "Authenticated");
			return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.OK);
		} else {
			logger.info("Is Authenticated : {}", isAuthenticated);
			AuthenticationResponse response = new AuthenticationResponse(username, "Not Authenticated");
			return new ResponseEntity<AuthenticationResponse>(response, HttpStatus.BAD_REQUEST);

		}
	}

	@PostMapping("/get")
	public RegisterUserEntity get(@RequestBody RegisterUserEntity request) {
		RegisterUserEntity result = this.userServ.get(request);
		return result;
	}

	/*
	 * @GetMapping("/{email},{pass}") public RegisterUserEntity
	 * checkUser(@RequestParam String email, @RequestParam String pass) {
	 * this.userServ.checkUser(email, pass); return null; }
	 */
}
