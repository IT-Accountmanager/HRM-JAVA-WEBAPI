package com.hrm.controllers;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
import com.hrm.models.Attendance;
import com.hrm.payloads.ApplyLeaveDto;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.payloads.managerAttendanceView;
import com.hrm.services.IAttendanceService;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/*@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
*/

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

	@Autowired
	IAttendanceService attendanceService;

	private static final Logger logger = LoggerFactory.getLogger(IAttendanceService.class);

	// ----------------FOR CLOCK IN------------------
	@PostMapping("/clockIn/{employeeId}")
	public ResponseEntity<String> addInTime(@PathVariable String employeeId) {
		logger.info("Start of addInTime {}", employeeId);
		String result = this.attendanceService.clockIn(employeeId);
		logger.info("End of addInTime {}", result);

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
	public ResponseEntity<List<UserAttendanceDto>> getAllAttendanceByEmployeeId(@PathVariable String employeeId) {
		try {
			List<UserAttendanceDto> allAttendance = attendanceService.allAttendance(employeeId);
			return new ResponseEntity<>(allAttendance, HttpStatus.OK);
		} catch (Exception e) {
			// Log the error
			logger.error("Error retrieving attendance for employeeId: {}", employeeId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// --------------------------POST BILLABLE HOURS-------------------------
	@PostMapping("/billableHours/{employeeId}")
	public ResponseEntity<String> addBillableHours(@RequestBody BillableHoursDto billableHoursDto,
			@PathVariable String employeeId) {
		String result = this.attendanceService.addBillableHours(billableHoursDto, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// --------------------------POST REGULARIZED HOURS-------------------------
	@PostMapping("/regularizationHours/{employeeId}")
	public ResponseEntity<String> addRegularizationHours(@RequestBody RegularizationHoursDto regularizationHoursDto,
			@PathVariable String employeeId) {
		String result = this.attendanceService.addRegularizationHours(regularizationHoursDto, employeeId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
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

	@PostMapping("/addLeave/{employeeId}")
	public ResponseEntity<String> addLeave(@RequestBody ApplyLeaveDto applyLeaveDto, @PathVariable String employeeId) {
		String result = attendanceService.addLeave(applyLeaveDto, employeeId);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// --------------------------GET APPLY LEAVE-------------------------
	@GetMapping("/getleave/{employeeId}")
	public ResponseEntity<ApplyLeaveDto> getLeave(@PathVariable String employeeId) {
		ApplyLeaveDto attendance = this.attendanceService.getLeave(employeeId);
		return new ResponseEntity<ApplyLeaveDto>(attendance, HttpStatus.OK);
	}

	// --------------------------GET BILLABLE HOURS-------------------------

	@GetMapping("/billablehours/{employeeId}")
	public ResponseEntity<BillableHoursDto> getBillableHours(@PathVariable String employeeId) {
		BillableHoursDto attendance = this.attendanceService.getBillableHours(employeeId);
		return new ResponseEntity<BillableHoursDto>(attendance, HttpStatus.OK);
	}

	// --------------------------GET REGULARISATION HOURS-------------------------

	@GetMapping("/getregularizationhours/{employeeId}")
	public ResponseEntity<RegularizationHoursDto> getRegularizationHours(@PathVariable String employeeId) {
		RegularizationHoursDto attendance = this.attendanceService.getRegularizationHours(employeeId);
		return new ResponseEntity<RegularizationHoursDto>(attendance, HttpStatus.OK);
	}

	@PostMapping("/getEmployeeHoursBilling")
	public String getEmployee(@RequestBody ObjectNode req) {

		String managerId = req.get("employeeId").asText();
		String month = req.get("month").asText();
		String attendance = this.attendanceService.getAttendanceAsJson(managerId, month);
		return attendance;
	}

	@GetMapping("/refresh/{employeeId}")
	public String getDuration(@PathVariable String employeeId) {
		String duration = this.attendanceService.getDuration(employeeId);
		return duration;
	}

//	@PostMapping("/managerattendance/{date}")
//	public ResponseEntity<String>addManagerAttendance ( @RequestBody ManagerAttendanceViewDto managerAttendanceViewDto, @PathVariable("date") LocalDate date) {
//	    String result = this.attendanceService.addManagerAttendance(managerAttendanceViewDto, date);
//		return new ResponseEntity<String>(result,HttpStatus.OK);
//	}

//	@PostMapping("/createOrUpdate/leave/{employeeId}")
//	public ResponseEntity<ApplyLeaveDto> createOrUpdateLeave(@PathVariable String employeeId,
//			@RequestBody ApplyLeaveDto leaveDto) {
//		// Set employeeId in the leaveDto
//		// leaveDto.setEmployeeId(employeeId);
//
//		ApplyLeaveDto updatedLeaveDto = attendanceService.createOrUpdateLeave(leaveDto);
//		return new ResponseEntity<>(updatedLeaveDto, HttpStatus.CREATED);
//	}

//>>>>>>> branch 'ramachandra' of https://github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git
//	@PostMapping("/regularization-hours/{employeeId}")
//    public ResponseEntity<String> addRegularizationHours(@RequestBody RegularizationHoursDto regularizationHoursDto,
//                                                         @PathVariable String employeeId) {
//        String response = attendanceService.addRegularizationHours(regularizationHoursDto, employeeId);
//        return ResponseEntity.ok(response);
//    }

//	@PostMapping("/add/leave/{employeeId}")
//	public ResponseEntity<ApplyLeaveDto> addLeave(@PathVariable String employeeId,
//			@RequestBody ApplyLeaveDto leaveDto) {
//		// Set employeeId in the leaveDto
//		// leaveDto.setEmployeeId(employeeId);
//
//		ApplyLeaveDto updatedLeaveDto = attendanceService.addLeave(leaveDto);
//		return new ResponseEntity<>(updatedLeaveDto, HttpStatus.CREATED);
//	}

//	@PostMapping("/billableHours/{employeeId}")
//	public ResponseEntity<String> addBillableHours(@RequestBody BillableHoursDto billableHoursDto,
//			@PathVariable String employeeId) {
//		String result = this.attendanceService.addBillableHours(billableHoursDto, employeeId);
//		return new ResponseEntity<String>(result, HttpStatus.OK);
//	}

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

}
