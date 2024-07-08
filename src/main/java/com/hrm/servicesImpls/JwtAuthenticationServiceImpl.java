package com.hrm.servicesImpls;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.hrm.exception.InvalidUserDetailsException;
import com.hrm.exception.UserAlreadyExistedException;
import com.hrm.models.UserEntity;
import com.hrm.payloads.AuthenticationRequest;
import com.hrm.payloads.AuthenticationTokenResponse;
import com.hrm.payloads.UserEntityRequest;
import com.hrm.repositories.IUserRepository;
import com.hrm.security.JwtTokenUtil;
import com.hrm.services.IJwtAuthenticationService;

@Service
public class JwtAuthenticationServiceImpl implements IJwtAuthenticationService {
	@Autowired
	IUserRepository userRepository;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private final Logger logger = LoggerFactory.getLogger(JwtAuthenticationServiceImpl.class);

	@Override
	public String registerUser(UserEntityRequest requestedUser) {

		if (requestedUser.getUsername() == null || requestedUser.getUsername().isEmpty()
				|| requestedUser.getPassword() == null || requestedUser.getPassword().isEmpty()) {
			logger.error("Username or password is not null or empty");
			throw new InvalidUserDetailsException("USERNAME AND PASSWORD MUST NOT BE NULL OR EMPTY");
		}

		Boolean isUserNameExisted = userRepository.existsByUsername(requestedUser.getUsername());
		logger.info("isUserNameExisted : {}", isUserNameExisted);

		if (!isUserNameExisted) {
			UserEntity user = new UserEntity();
			BeanUtils.copyProperties(requestedUser, user);
			user.setPassword(new BCryptPasswordEncoder().encode(requestedUser.getPassword()));
			userRepository.save(user);
			return "USER " + requestedUser.getUsername() + " REGISTERD SUCCESSFULLY !";
		} else {
			logger.debug("TRY ELSE BLOCK WITH ");
			throw new UserAlreadyExistedException("USERNAME " + requestedUser.getUsername() + " IS ALREADY EXISTED.");
		}
	}

	@Override
	public AuthenticationTokenResponse authenticateUser(AuthenticationRequest authenticationRequest) {
		if (authenticationRequest.getUsername() == null || authenticationRequest.getUsername().isEmpty()
				|| authenticationRequest.getPassword() == null || authenticationRequest.getPassword().isEmpty()) {
			logger.debug("Username or password is not null or empty");
			throw new InvalidUserDetailsException("USERNAME OR PASSWORD MUST NOT BE EMPTY");
		}

		try {
			authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
			logger.debug("User {} is Authenticated.", authenticationRequest.getUsername());

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
			logger.info("Username loaded succesfully by userDetailsService : {}", userDetails);

			final String generatedToken = jwtTokenUtil.generateToken(userDetails.getUsername());
			logger.info("Token {} generated successfully for user {} ", generatedToken, userDetails.getUsername());

			return new AuthenticationTokenResponse(generatedToken);

		} catch (Exception e) {
			logger.error("Exception occurred during authentication: {}", e.getMessage());
			throw e;
		}

	}

	private void authenticate(String username, String password)
			throws InvalidUserDetailsException, AuthenticationException {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			logger.debug("User {} is disabled - {}", username, e.getMessage());
			throw e;
		} catch (BadCredentialsException e) {
			logger.debug("Invalid credentials provided by User {} : {}", username, e.getMessage());
			throw e;
		}
	}
}
