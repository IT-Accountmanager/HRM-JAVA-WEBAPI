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
import com.hrm.main.models.Attendance;
import com.hrm.main.services.IAttendanceService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	IAttendanceService attendanceService;

	// ----------------FOR CLOCK IN------------------
	@PostMapping("/clockIn/{employeeId}")
	public ResponseEntity<String> addInTime(@PathVariable String employeeId) {
		String result = this.attendanceService.clockIn(employeeId);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------FOR GET CLOCK IN------------------
	@GetMapping("/getClockIn/{employeeId}")
	public ResponseEntity<Attendance> getAttendance(@PathVariable String employeeId) {
		Attendance attendance = this.attendanceService.getAttendance(employeeId);
		return new ResponseEntity<Attendance>(attendance, HttpStatus.OK);
	}

	// ----------------FOR CLOCK OUT------------------
	@PostMapping("/clockOut/{employeeId}")
	public ResponseEntity<String> addOutTime(@PathVariable String employeeId) {
		String result = this.attendanceService.clockOut(employeeId);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/allattendance")
	public ResponseEntity<List<Attendance>> getAllAttendence() {
		List<Attendance> allAttendance = this.attendanceService.allAttendance();
		return new ResponseEntity<List<Attendance>>(allAttendance, HttpStatus.OK);
	}

	@PutMapping("/editattendance/{id}")
	public ResponseEntity<String> editAttendance(@RequestBody Attendance attendance, @PathVariable Integer id) {
		String editAttendance = this.attendanceService.editAttendance(attendance, id);
		return new ResponseEntity<String>(editAttendance, HttpStatus.OK);
	}

	@DeleteMapping("/deleteattendance/{id}")
	public ResponseEntity<String> deletAttendance(@PathVariable int id) {
		String result = this.attendanceService.deleteAttendance(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);

	}
}
