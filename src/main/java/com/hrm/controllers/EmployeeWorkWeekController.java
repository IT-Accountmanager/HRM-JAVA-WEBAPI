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

import com.hrm.payloads.EmployeeWorkWeekDto;
import com.hrm.services.EmployeeWorkWeekService;


@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/add")

public class EmployeeWorkWeekController {
	@Autowired
	EmployeeWorkWeekService employeeWorkWeekService;
	
	@PostMapping("/employee/workweek/{employeeId}")
    public ResponseEntity<String> addWorkWeek(@RequestBody EmployeeWorkWeekDto employeeWorkWeekDto, @PathVariable String employeeId) {
        String resultMessage = employeeWorkWeekService.addWorkWeek(employeeWorkWeekDto, employeeId);
        return new ResponseEntity<>(resultMessage, HttpStatus.OK);
    }
	
	@GetMapping("/get/employeeworkweek/{employeeId}")
	public ResponseEntity<EmployeeWorkWeekDto> getWorkWeek(@PathVariable String employeeId) {
		EmployeeWorkWeekDto employeeWorkWeekDto = employeeWorkWeekService.getWorkWeek(employeeId);
		return ResponseEntity.ok(employeeWorkWeekDto);
	}
	
	
	
//	@PostMapping("/workweek")
//	public ResponseEntity<EmployeeWorkWeek> addWorkWeek(@RequestBody EmployeeWorkWeek workWeek) {
//	EmployeeWorkWeek result = this.employeeWorkWeekService.addWorkWeek(workWeek);
//	
//	return new ResponseEntity<>(result, HttpStatus.OK);


//	}
}
