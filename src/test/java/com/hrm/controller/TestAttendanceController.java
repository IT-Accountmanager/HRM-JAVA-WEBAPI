package com.hrm.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.hrm.controllers.AttendanceController;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.services.IAttendanceService;

public class TestAttendanceController {

	@Mock
	private IAttendanceService attendanceService;

	@InjectMocks
	private AttendanceController attendanceController;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetAllAttendance() {

		ObjectNode requestData = new ObjectMapper().createObjectNode();
		requestData.put("employeeId", "123");
		requestData.put("month", 5);
		requestData.put("year", 2024);

		Set<UserAttendanceDto> mockAttendance = new HashSet<>();

		when(attendanceService.allAttendance("123", 5, 2024)).thenReturn(mockAttendance);

		ResponseEntity<Set<UserAttendanceDto>> responseEntity = attendanceController.getAllAttendance(requestData);

		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertEquals(mockAttendance, responseEntity.getBody());

		verify(attendanceService, times(1)).allAttendance("123", 5, 2024);
	}
}