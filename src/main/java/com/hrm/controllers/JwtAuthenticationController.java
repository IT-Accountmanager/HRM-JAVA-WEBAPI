package com.hrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.payloads.AuthenticationRequest;
import com.hrm.payloads.AuthenticationTokenResponse;
import com.hrm.payloads.UserEntityRequest;
import com.hrm.services.IJwtAuthenticationService;

import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

	@Autowired
	private IJwtAuthenticationService authenticationService;

	@Operation(summary = "TOKEN")
	@PostMapping(value = "/token", consumes = { "application/json" })
	public ResponseEntity<AuthenticationTokenResponse> authenticate(
			@RequestBody AuthenticationRequest authenticationRequest) {
		return new ResponseEntity<AuthenticationTokenResponse>(
				this.authenticationService.authenticateUser(authenticationRequest), HttpStatus.CREATED);
	}

	@Operation(summary = "REGISTER USER")
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody UserEntityRequest user) {
		return new ResponseEntity<String>(this.authenticationService.registerUser(user), HttpStatus.OK);
	}

}
