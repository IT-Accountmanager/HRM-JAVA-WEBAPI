package com.hrm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.Helper.EnumCollection.Departments;
import com.hrm.services.IStructureService;

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

	/*
	 * @GetMapping("/getEmployeeBySubDepartment/{subDepartment}") public
	 * ResponseEntity<List<String>> findEmployee(@PathVariable Sub_Department
	 * subDepartment) { List<String> employee =
	 * this.structureService.findEmployeesBySubDepartment(subDepartment); return new
	 * ResponseEntity<List<String>>(employee, HttpStatus.OK); }
	 */

	/*
	 * @GetMapping("/getManagerByDepartment/{department}/{subDepartment}/{manager}")
	 * public ResponseEntity<List<String>> findManager(@PathVariable Departments
	 * department,
	 * 
	 * @PathVariable Sub_Department subDepartment, @PathVariable String manager) {
	 * List<String> result =
	 * this.structureService.findManagerByDepartment(department, subDepartment,
	 * manager); return new ResponseEntity<List<String>>(result, HttpStatus.OK); }
	 */

	/*
	 * @GetMapping(
	 * "/getEmployeeByDepartment/{department}/{subDepartment}/{designation}") public
	 * ResponseEntity<List<String>> findEmployees(@PathVariable Departments
	 * department,
	 * 
	 * @PathVariable Sub_Department subDepartment, @PathVariable String designation)
	 * { List<String> result =
	 * this.structureService.findEmployeesByDepartment(department, subDepartment,
	 * designation); return new ResponseEntity<List<String>>(result, HttpStatus.OK);
	 * }
	 */

}
