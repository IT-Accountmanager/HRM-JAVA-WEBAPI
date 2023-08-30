package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.AttendanceSummary;
import com.hrm.main.services.IAttendanceSummaryService;
@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/attendance")
public class AttendanceSummaryController {

	@Autowired
	IAttendanceSummaryService attendanceService;

	@PostMapping("/addattendance")
	public ResponseEntity<String> addAttendance(@RequestBody AttendanceSummary attendance) {
		String result = this.attendanceService.addAttendence(attendance);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/allattendance")
	public ResponseEntity<List<AttendanceSummary>> getAllAttendence() {
		List<AttendanceSummary> allAttendance = this.attendanceService.allAttendance();
		return new ResponseEntity<List<AttendanceSummary>>(allAttendance, HttpStatus.OK);
	}

	@GetMapping("/attendance/{id}")
	public ResponseEntity<AttendanceSummary> getAttendance(@PathVariable Integer id) {

		AttendanceSummary attendance = this.attendanceService.getAttendance(id);
		return new ResponseEntity<AttendanceSummary>(attendance, HttpStatus.OK);
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity<String> editAttendance(@RequestBody AttendanceSummary attendance, @PathVariable Integer id) {
		String editAttendance = this.attendanceService.editAttendance(attendance, id);
		return new ResponseEntity<String>(editAttendance, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deletAttendance(@PathVariable int id) {
		String result = this.attendanceService.deleteAttendance(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
}
