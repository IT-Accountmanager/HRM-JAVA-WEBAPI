package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.DemoEmployee;
import com.hrm.main.services.DemoEmployeeService;

@RestController
@RequestMapping(value = "/DemoEmp")
public class DemoEmployeeController {

	@Autowired
	DemoEmployeeService demoEmpSer;
	

	// DemoEmployee Post Api

	@PostMapping("/")
	public ResponseEntity<DemoEmployee> AddEmp(@RequestBody DemoEmployee dEmp) {
		DemoEmployee result = this.demoEmpSer.addEmp(dEmp);
		return new ResponseEntity<DemoEmployee>(result, HttpStatus.CREATED);
	}

	// DemoEmployee Get Api
	@GetMapping("/")
	public ResponseEntity<List<DemoEmployee>> getEmps() {
		List<DemoEmployee> result = this.demoEmpSer.getEmps();
		return new ResponseEntity<List<DemoEmployee>>(result, HttpStatus.OK);

	}

	// DemoEmployee Update Api
	@PutMapping("/{eId}")
	public ResponseEntity<DemoEmployee> updateEmp(@RequestBody DemoEmployee dEmp , @PathVariable Integer eId ) {
		 DemoEmployee updateEmp = this.demoEmpSer.updateEmp(dEmp, eId);
		return new ResponseEntity<DemoEmployee>(updateEmp, HttpStatus.OK);
	}
	
	// DemoEmployee Update Api
@DeleteMapping("/{eId}")
public void deleteEmp(@PathVariable Integer eId)
{
	this.demoEmpSer.deleteEmp(eId);
}
}
