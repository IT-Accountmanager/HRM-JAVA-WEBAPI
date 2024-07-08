package com.hrm.services;

import com.hrm.payloads.AuthenticationRequest;
import com.hrm.payloads.AuthenticationTokenResponse;
import com.hrm.payloads.UserEntityRequest;

public interface IJwtAuthenticationService {

	// Boolean registerUser(UserEntityRequest user);

	String registerUser(UserEntityRequest user);

	AuthenticationTokenResponse authenticateUser(AuthenticationRequest authenticationRequest);

}
