package com.hrm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hrm.controllers.JwtAuthenticationController;
import com.hrm.payloads.AuthenticationRequest;
import com.hrm.payloads.AuthenticationTokenResponse;
import com.hrm.services.IJwtAuthenticationService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = JwtAuthenticationController.class)
public class TestJwtAuthenticationController {

	@MockBean
	private IJwtAuthenticationService authenticationService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void authenticateTest() throws Exception {

		AuthenticationTokenResponse response = new AuthenticationTokenResponse("12345");
		when(authenticationService.authenticateUser(ArgumentMatchers.any())).thenReturn(response);

		AuthenticationRequest authenticationRequest = new AuthenticationRequest("TestUser", "password");
		// Convert authenticationRequest in json format
		ObjectMapper mapper = new ObjectMapper();
		// For java to json
		String authenticationRequestJson = mapper.writeValueAsString(authenticationRequest);

		// Preparing REQUEST
		MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/auth/token")
				.contentType(MediaType.APPLICATION_JSON).content(authenticationRequestJson);

		ResultActions perform = mockMvc.perform(requestBuilder);

		MvcResult andReturn = perform.andReturn();

		MockHttpServletResponse response2 = andReturn.getResponse();

		assertEquals("12345", response2.getsta);

	}

	// @Test
//	public void authenticateTest() throws Exception {
//		// Prepare request JSON
//		AuthenticationRequest request = new AuthenticationRequest("TestUser", "password");
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonRequest = mapper.writeValueAsString(request);
//
//		// Prepare response
//		AuthenticationTokenResponse response = new AuthenticationTokenResponse();
//		response.setToken("Token-12345");
//
//		// Mock service method
//		// when(jwtAuthenticationService.authenticateUser(ArgumentMatchers.any())).thenReturn(response);
//		when(jwtAuthenticationService.authenticateUser(request)).thenReturn(response);
//
//		// Build request with CSRF token
//		MvcResult result = mockMvc
//				.perform(post("/auth/token").contentType(MediaType.APPLICATION_JSON).content(jsonRequest)
//						.with(SecurityMockMvcRequestPostProcessors.csrf())) // Include CSRF token in the request
//				.andExpect(status().isCreated()).andExpect(jsonPath("$.token").value("Token-12345")).andReturn();
//
//		// Verify response status
//		MockHttpServletResponse response2 = result.getResponse();
//		int status = response2.getStatus();
//		assertEquals(201, status);
//	}

//	@Test
//	public void authenticateTest() throws Exception {
//
//		AuthenticationRequest request = new AuthenticationRequest("TestUser", "password");
//		ObjectMapper mapper = new ObjectMapper();
//		String jsonRequest = mapper.writeValueAsString(request);
//
//		AuthenticationTokenResponse response = new AuthenticationTokenResponse();
//		response.setToken("Token-12345");
//
//		when(jwtAuthenticationService.authenticateUser(ArgumentMatchers.any())).thenReturn(response);
//f
////		MockHttpServletRequestBuilder reqBuilder = MockMvcRequestBuilders.post("/auth/token")
////				.contentType(MediaType.APPLICATION_JSON).content(jsonRequest);
////
////		ResultActions perform = mockMvc.perform(reqBuilder);
////
////		perform.andExpect(status().isCreated()).andExpect(jsonPath("$.token").value("Token-12345"));
//
//		 MvcResult result = mockMvc.perform(post("/auth/token")
//	                .contentType(MediaType.APPLICATION_JSON)
//	                .content(jsonRequest)
//	                .with(SecurityMockMvcRequestBuilders.csrf()))  // Include CSRF token in the request
//	                .andExpect(status().isCreated())
//	                .andExpect(jsonPath("$.token").value("Token-12345"))
//	                .andReturn();
//
//		MockHttpServletResponse response2 = andReturn.getResponse();
//
//		int status = response2.getStatus();
//
//		assertEquals(201, status);
//
//	}

}
