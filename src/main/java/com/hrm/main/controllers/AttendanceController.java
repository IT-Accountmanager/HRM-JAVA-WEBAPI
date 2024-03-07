package com.hrm.main.controllers;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.hrm.main.models.Attendance;
import com.hrm.main.models.Employee;
import com.hrm.main.payloads.ApplyLeaveDto;
import com.hrm.main.payloads.AttendanceEmployeeDto;
import com.hrm.main.payloads.BillableHoursDto;
import com.hrm.main.payloads.RegularizationHoursDto;
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
		LocalDate currentDate = LocalDate.now();
		String result = this.attendanceService.clockIn(employeeId, currentDate);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ----------------FOR GET CLOCK IN------------------
	@GetMapping("/getClockIn/{employeeId}")
	public ResponseEntity<AttendanceEmployeeDto> getAttendance(@PathVariable String employeeId) {
		AttendanceEmployeeDto attendance = this.attendanceService.getAttendance(employeeId);
		return new ResponseEntity<AttendanceEmployeeDto>(attendance, HttpStatus.OK);
	}

	// ----------------FOR CLOCK OUT------------------
	@PostMapping("/clockOut/{employeeId}")
	public ResponseEntity<String> addOutTime(@PathVariable String employeeId) {
		String result = this.attendanceService.clockOut(employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// -------------------GET ALL ATTENDANCE BY EMPLOYEE ID-------------------
	@GetMapping("/allattendance/{employeeId}")
	public ResponseEntity<List<Attendance>> getAllAttendenceByEmployeeId(@PathVariable String employeeId) {
		List<Attendance> allAttendance = this.attendanceService.allAttendance(employeeId);
		return new ResponseEntity<List<Attendance>>(allAttendance, HttpStatus.OK);
	}

	// --------------------------POST BILLABLE HOURS-------------------------
	@PostMapping("/billableHours/{employeeId}")
	public ResponseEntity<String> addBillableHours(@RequestBody BillableHoursDto billableHoursDto,
			@PathVariable String employeeId) {
		String result = this.attendanceService.addBillableHours(billableHoursDto, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// --------------------------POST REGULARIZED HOURS-------------------------
//	@PostMapping("/regularizationHours/{employeeId}")
//	public ResponseEntity<String> addRegularizationHours(@RequestBody RegularizationHoursDto regularizationHoursDto,
//			@PathVariable String employeeId) {
//		String result = this.attendanceService.addRegularizationHours(regularizationHoursDto, employeeId);
//		return new ResponseEntity<String>(result, HttpStatus.OK);
//	}

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

	// --------------------------POST APPLY LEAVE-------------------------

	@PostMapping("/add/leave/{employeeId}")
	public ResponseEntity<ApplyLeaveDto> addLeave(@PathVariable String employeeId,
			@RequestBody ApplyLeaveDto leaveDto) {
		// Set employeeId in the leaveDto
		// leaveDto.setEmployeeId(employeeId);

		ApplyLeaveDto updatedLeaveDto = attendanceService.addLeave(leaveDto);
		return new ResponseEntity<>(updatedLeaveDto, HttpStatus.CREATED);
	}

//	----------------------------POST BILLABLE HOURS------------------------

//	 @PostMapping("/add/{employeeId}")
//	    public ResponseEntity<String> addBillableHours(@RequestBody BillableHoursDto billableHoursDto, @PathVariable String employeeId) {
//	        String result = attendanceService.addBillableHours(billableHoursDto, employeeId);
//	        return ResponseEntity.ok(result);
//	    }
	
//	---------------------------GET TOTAL HOURS-----------------------------

//	@GetMapping("/total-hours/{employeeId}")
//	public ResponseEntity<Float> getTotalHours(@PathVariable String employeeId) {
//		float attendance = this.attendanceService.gettotalHoursFromBillableHoursDto(employeeId);
//
//		return new ResponseEntity<Float>(attendance, HttpStatus.OK);
//	}
	
//	----------------------------------GET CLOCKIN TIME FOR REGULARIZATION-----------------------------
//	@GetMapping("/clockinTime/{employeeId}")
//	public ResponseEntity<LocalTime> getinTime(@PathVariable String employeeId) {
//		LocalTime attendance = this.attendanceService.getinTimeFromAttendance(employeeId);
//		return new ResponseEntity<LocalTime>(attendance, HttpStatus.OK);
//	}
	
//	----------------------------------GET CLOCK OUT TIME FOR REGULARIZATION-----------------------------
//	@GetMapping("/clockoutTime/{employeeId}")
//	public ResponseEntity<LocalTime> getoutTime(@PathVariable String employeeId) {
//		LocalTime attendance = this.attendanceService.getoutTimeFromAttendance(employeeId);
//		return new ResponseEntity<LocalTime>(attendance, HttpStatus.OK);
//	}
	
	@PostMapping("/regularization-hours/{employeeId}")
    public ResponseEntity<String> addRegularizationHours(@RequestBody RegularizationHoursDto regularizationHoursDto,
                                                         @PathVariable String employeeId) {
        String response = attendanceService.addRegularizationHours(regularizationHoursDto, employeeId);
        return ResponseEntity.ok(response);
    }

}
