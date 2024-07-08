package com.hrm.controllers;

import java.util.List;

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
import com.hrm.payloads.LeaveDetailsRequestDto;
import com.hrm.payloads.LeaveRequestDetailsDto;
import com.hrm.payloads.ManagerLeaveDetailsDto;
import com.hrm.payloads.ManagerLeaveEditDto;
import com.hrm.services.LeaveManagementService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/leave")

public class LeaveManagementTableController {
	@Autowired
	LeaveManagementService leaveManagementService;

	@PostMapping("/addLeave")
	public ResponseEntity<String> addLeave(@RequestBody ApplyLeaveDto applyLeaveDto) {
		String result = leaveManagementService.addLeave(applyLeaveDto);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	/*
	 * @GetMapping("manager/getleave/{managerId}/{employeeId}/{year}/{month}")
	 * public ResponseEntity<List<ManagerLeaveDetailsDto>> getLeave(@PathVariable
	 * String managerId,
	 * 
	 * @PathVariable String employeeId, LocalDate year, Month month) {
	 * List<ManagerLeaveDetailsDto> result =
	 * this.leaveManagementService.getLeaveDetails(managerId, employeeId, year,
	 * month); return new ResponseEntity<List<ManagerLeaveDetailsDto>>(result,
	 * HttpStatus.OK); }
	 */

	@PostMapping("/getLeaveDetails")
	public ResponseEntity<List<ManagerLeaveDetailsDto>> getLeave(
			@RequestBody LeaveDetailsRequestDto leaveDetailsRequestDto) {
		List<ManagerLeaveDetailsDto> result = this.leaveManagementService.getLeaveDetails(leaveDetailsRequestDto);
		return new ResponseEntity<List<ManagerLeaveDetailsDto>>(result, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/getLeaveRequestDetails/{id}") public
	 * ResponseEntity<LeaveRequestDetailsDto> leaveRequestDetails(@PathVariable int
	 * id){
	 * 
	 * }
	 */

	@PostMapping("manager/editLeaveRequest/{id}")
	public String editLeaveRequest(@PathVariable int id, @RequestBody ManagerLeaveEditDto managerLeaveEditDto) {
		String result = this.leaveManagementService.editLeaveRequest(id, managerLeaveEditDto);
		return result;

	}

	@GetMapping("leavedetails/{id}")
	public ResponseEntity<LeaveRequestDetailsDto> getLeaveDetails(@PathVariable("id") int id) {
		LeaveRequestDetailsDto leaveDetailsDto = leaveManagementService.getLeaveDetails(id);

		if (leaveDetailsDto != null) {
			return ResponseEntity.ok(leaveDetailsDto);
		} else {
			return ResponseEntity.noContent().build();
		}
	}
}
