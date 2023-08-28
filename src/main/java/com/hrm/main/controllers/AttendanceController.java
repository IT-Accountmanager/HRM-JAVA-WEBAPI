package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Attendance;
import com.hrm.main.services.IAttendanceService;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	IAttendanceService attendanceService;

	@PostMapping("/addattendance")
	public ResponseEntity<String> addAttendance(@RequestBody Attendance attendance) {
		String result = this.attendanceService.addAttendence(attendance);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/allattendance")
	public ResponseEntity<List<Attendance>> getAllAttendence() {
		List<Attendance> allAttendance = this.attendanceService.allAttendance();
		return new ResponseEntity<List<Attendance>>(allAttendance, HttpStatus.OK);
	}

}
