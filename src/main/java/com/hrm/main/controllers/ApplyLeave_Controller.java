package com.hrm.main.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.ApplyLeave_Entity;
import com.hrm.main.services.ApplyLeave_Service;


@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/leave")

public class ApplyLeave_Controller {
	@Autowired
	ApplyLeave_Service applyLeave_Service;
	
	@PostMapping("/table")
	public ApplyLeave_Entity addLeave(@RequestBody ApplyLeave_Entity applyLeave_Entity) {
		return applyLeave_Service.addLeave(applyLeave_Entity);
	}
	
	@GetMapping("/all")
	public List<ApplyLeave_Entity>getAllLeaves(){
		return applyLeave_Service.getAllLeave();
	}

}
