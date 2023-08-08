package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.hrm.main.services.EmployeeService;

@RestController
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
}
