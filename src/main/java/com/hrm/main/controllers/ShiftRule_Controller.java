package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.ShiftRule_Entity;
import com.hrm.main.services.ShiftRule_Service;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/shiftrule")

public class ShiftRule_Controller {
	@Autowired
	ShiftRule_Service shiftRule_Service;
	
	@PostMapping("/table")
	public ShiftRule_Entity addShiftRule(@RequestBody ShiftRule_Entity shiftRule_Entity) {
		return shiftRule_Service.addShiftRule(shiftRule_Entity);
	}
	
	@GetMapping("/get")
	public List<ShiftRule_Entity>getAllShift(){
		return shiftRule_Service.getAllShiftRule();
	}

}
