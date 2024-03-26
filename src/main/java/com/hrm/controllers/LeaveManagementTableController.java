package com.hrm.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.services.LeaveManagementService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/leave")

public class LeaveManagementTableController {
	@Autowired
	LeaveManagementService leaveManagementService;
	
	
	@PostMapping("/addLeave/{employeeId}")
	public ResponseEntity<String> addLeave(@RequestBody ApplyLeaveDto applyLeaveDto, @PathVariable String employeeId) {
		String result = leaveManagementService.addLeave(applyLeaveDto, employeeId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getleave/{employeeId}")
	public ResponseEntity<ApplyLeaveDto> getLeave(@PathVariable String employeeId) {
		ApplyLeaveDto attendance = this.leaveManagementService.getLeave(employeeId);
		return new ResponseEntity<ApplyLeaveDto>(attendance, HttpStatus.OK);
	}

}
