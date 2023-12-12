package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.Helper.EnumCollection.Departments;
import com.hrm.main.services.IStructureService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Structure")
public class StructureController {

	@Autowired
	IStructureService structureService;

	@GetMapping("/getEmployeeByDepartment/{department}")
	public ResponseEntity<List<String>> findEmployee(@PathVariable Departments department) {
		List<String> employee = this.structureService.findEmployeesByDepartment(department);
		return new ResponseEntity<List<String>>(employee, HttpStatus.OK);
	}

}
