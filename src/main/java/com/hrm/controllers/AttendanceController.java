package com.hrm.controllers;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Set;

import org.bouncycastle.asn1.ocsp.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException.BadRequest;

import com.hrm.exception.ResourceNotFoundException;
import com.hrm.models.Attendance;
import com.hrm.payloads.AttendanceEmployeeDto;
import com.hrm.payloads.AttendanceRequestDto;
import com.hrm.payloads.AttendanceSummaryDto;
import com.hrm.payloads.BillableHoursDto;
import com.hrm.payloads.ManagerAttendanceDetailsDto;
import com.hrm.payloads.ManagerAttendanceEditDto;
import com.hrm.payloads.ManagerAttendanceSummaryDto;
import com.hrm.payloads.RegularizationHoursDto;
import com.hrm.payloads.RegularizationManagerEditDto;
import com.hrm.payloads.UserAttendanceDto;
import com.hrm.payloads.UserAttendanceSummaryDto;
import com.hrm.payloads.ManagerAttendanceViewDto;
import com.hrm.services.IAttendanceService;
import com.hrm.utils.ErrorResponse;
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
	public ResponseEntity<Set<UserAttendanceDto>> getAllAttendanceByEmployeeId(@PathVariable String employeeId) {
		try {
			Set<UserAttendanceDto> allAttendance = attendanceService.allAttendance(employeeId);
			return new ResponseEntity<>(allAttendance, HttpStatus.OK);
		} catch (Exception e) {
			// Log the error
			logger.error("Error retrieving attendance for employeeId: {}", employeeId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/userAllAttendance")
	public ResponseEntity<Set<UserAttendanceDto>> getAllAttendance(@RequestBody ObjectNode req) {
		String employeeId = req.get("employeeId").asText();
		Integer month = req.get("month").asInt();
		Integer year = req.get("year").asInt();
//		int a = req.get("a").asInt();
		try {
			Set<UserAttendanceDto> allAttendance = attendanceService.allAttendance(employeeId, month, year);
			return new ResponseEntity<>(allAttendance, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error retrieving attendance for employeeId: {}", employeeId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	// Admin Summary
	@PostMapping("/Summary")
	public ResponseEntity<List<AttendanceSummaryDto>> getSummary(@RequestBody ObjectNode request) {
		Integer month = request.get("month").asInt();
		Integer year = request.get("year").asInt();
		try {
			List<AttendanceSummaryDto> result = this.attendanceService.getSummary(month, year);
			return new ResponseEntity<List<AttendanceSummaryDto>>(result, HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Error retrieving attendance for Month : {} Year : {}", month, year, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/userSummary")
	public ResponseEntity<?> getUserAttendanceSummary(@RequestBody ObjectNode request) {
		String employeeId = request.get("employeeId").asText();
		Integer year = request.get("year").asInt();

		if (employeeId == null || employeeId.isEmpty()) {
			logger.error("Employee ID is missing or empty");
			String errorMessage = "EmployeeId is missing or empty";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		try {
			List<UserAttendanceSummaryDto> result = this.attendanceService.getSummary(employeeId, year);
			logger.info("Attendance Summary retrieved successfully for employeeId: {} in year: {}", employeeId, year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error("Error retrieving Attendance Summary of : {} , for : {}. Error : {} ", employeeId, year,
					e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	// Manager Summary
	@PostMapping("/managerSummary")
	public ResponseEntity<?> getManagerAttendanceSummary(@RequestBody ObjectNode request) {
		String managerId = request.get("managerId").asText();
		Integer year = request.get("year").asInt();

		if (managerId == null || managerId.isEmpty()) {
			logger.error("Manager Id is missing or empty.");
			String errorMessage = "Manager Id is missing or empty.";
			ErrorResponse errorResponse = new ErrorResponse(errorMessage, HttpStatus.BAD_REQUEST.value());
			return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
		}

		try {
			List<ManagerAttendanceSummaryDto> result = this.attendanceService.getManagerSummary(managerId, year);
			logger.info("Manager Attendance Summary retrieved successfully for manager Id: {} in year: {}", managerId,
					year);
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			logger.error("Error retriving Manager Attendance Summary of : {} , for : {} . Error : {}", managerId, year,
					e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/allattendance")
	public ResponseEntity<List<ManagerAttendanceDetailsDto>> getAttendance(
			@RequestBody AttendanceRequestDto attendanceRequestDto) {
		List<ManagerAttendanceDetailsDto> result = this.attendanceService.getAttendance(attendanceRequestDto);
		return new ResponseEntity<List<ManagerAttendanceDetailsDto>>(result, HttpStatus.OK);
	}

	// --------------------------POST BILLABLE HOURS-------------------------
	@PostMapping("/billableHours/{employeeId}")
	public ResponseEntity<?> addBillableHours(@RequestBody BillableHoursDto billableHoursDto,
			@PathVariable String employeeId) {
		try {
			String result = this.attendanceService.addBillableHours(billableHoursDto, employeeId);
			logger.info("Billable hours added successfully for employee with ID: {}", employeeId);
			return new ResponseEntity<String>(result, HttpStatus.OK);
		} catch (ResourceNotFoundException e) {
			// throw e;
			// return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
			logger.error("ResourceNotFoundException occurred: {}", e.getMessage());
			throw new ResourceNotFoundException("Employee Not Found By Employee Id : " + employeeId);
		}
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

//	@PostMapping("/addLeave/{employeeId}")
//	public ResponseEntity<String> addLeave(@RequestBody ApplyLeaveDto applyLeaveDto, @PathVariable String employeeId) {
//		String result = attendanceService.addLeave(applyLeaveDto, employeeId);
//		return new ResponseEntity<>(result, HttpStatus.OK);
//	}

	// --------------------------GET APPLY LEAVE-------------------------
//	@GetMapping("/getleave/{employeeId}")
//	public ResponseEntity<ApplyLeaveDto> getLeave(@PathVariable String employeeId) {
//		ApplyLeaveDto attendance = this.attendanceService.getLeave(employeeId);
//		return new ResponseEntity<ApplyLeaveDto>(attendance, HttpStatus.OK);
//	}

	// --------------------------GET BILLABLE HOURS-------------------------

	@GetMapping("/billablehours/{employeeId}/{date}")
	public ResponseEntity<BillableHoursDto> getBillableHours(@PathVariable String employeeId, LocalDate date) {
		BillableHoursDto attendance = this.attendanceService.getBillableHours(employeeId, date);
		return new ResponseEntity<BillableHoursDto>(attendance, HttpStatus.OK);
	}

	// --------------------------GET REGULARISATION HOURS-------------------------

	@GetMapping("/getregularizationhours/{employeeId}/{date}")
	public ResponseEntity<?> getRegularizationHours(@PathVariable String employeeId, @PathVariable LocalDate date) {
		RegularizationHoursDto attendance = this.attendanceService.getRegularizationHours(employeeId, date);
		if (attendance != null) {
			return ResponseEntity.ok().body(attendance);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Regularization Hours Record not found for employee " + employeeId + " on date " + date);
		}

	}

	@PostMapping("/getEmployeeHoursBilling")
	public String getEmployee(@RequestBody ObjectNode req) {
		logger.info("Start of get Employee : " + req);

		String managerId = req.get("employeeId").asText();
		String month = req.get("month").asText();
		String attendance = this.attendanceService.getAttendanceAsJson(managerId, month);
		return attendance;
	}

	// --------------------------FOR PUT MAPPING (APPROVEDHRSFOR BILLING AND
	// REMARKS)-------------------------

	@PutMapping("/editRemarks/{employeeId}")
	public ResponseEntity<ManagerAttendanceEditDto> editManagerAttendance(
			@RequestBody ManagerAttendanceEditDto managerAttendanceEditDto, @PathVariable String employeeId) {
		ManagerAttendanceEditDto attendance = this.attendanceService.editManagerAttendance(managerAttendanceEditDto,
				employeeId);
		return new ResponseEntity<ManagerAttendanceEditDto>(attendance, HttpStatus.OK);
	}

	@GetMapping("/refresh/{employeeId}")
	public String getDuration(@PathVariable String employeeId) {
		String duration = this.attendanceService.getDuration(employeeId);
		return duration;
	}

	@GetMapping("/manager/{managerId}/{month}")

	public ResponseEntity<?> getAttendanceByManagerAndMonth(@PathVariable String managerId,
			@PathVariable(required = false) String month) {
		try {
			List<ManagerAttendanceViewDto> attendanceDtoList = attendanceService
					.findAttendanceByManagerAndMonth(managerId, month);
			return ResponseEntity.ok().body(attendanceDtoList);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An error occurred while processing attendance data");
		}
	}

	@GetMapping("/getmanagerAttendance/{employeeId}/{date}")
	public ResponseEntity<ManagerAttendanceEditDto> getManagerAttendance(@PathVariable String employeeId,
			@PathVariable String date) {
		LocalDate parsedDate = LocalDate.parse(date);
		ManagerAttendanceEditDto managerAttendanceDto = attendanceService.getManagerAttendance(employeeId, parsedDate);
		return ResponseEntity.ok(managerAttendanceDto);
	}

//	------------------------PUT MAPPING FOR ACCEPTING OF REGULARIZATION----------------------
	@PutMapping("/editregularization/{employeeId}")
	public ResponseEntity<RegularizationManagerEditDto> editregularization(
			@RequestBody RegularizationManagerEditDto regularizationManagerEditDto, @PathVariable String employeeId) {
		RegularizationManagerEditDto attendance = this.attendanceService
				.editregularization(regularizationManagerEditDto, employeeId);
		return new ResponseEntity<RegularizationManagerEditDto>(attendance, HttpStatus.OK);
	}

//	------------------------PUT MAPPING FOR REJECTION OF REGULARIZATION----------------------
	@PutMapping("/editregularizationreject/{employeeId}")
	public ResponseEntity<RegularizationManagerEditDto> editregularizationreject(
			@RequestBody RegularizationManagerEditDto regularizationManagerEditDto, @PathVariable String employeeId) {
		RegularizationManagerEditDto attendance = this.attendanceService
				.editregularizationreject(regularizationManagerEditDto, employeeId);
		return new ResponseEntity<RegularizationManagerEditDto>(attendance, HttpStatus.OK);
	}

//github.com/IT-Accountmanager/HRM-JAVA-WEBAPI.git
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
