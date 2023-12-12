package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.ResignationInfo;
import com.hrm.main.models.WorkHistory;
import com.hrm.main.services.IResignationInfoService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/profession")
public class ResignationController {
	@Autowired
	private IResignationInfoService resignationService;

	@PostMapping("/resignationinfo/add")
	public ResponseEntity<String> addResignationInfo(@RequestBody ResignationInfo resignInfo) {
		String addResignationInfo = resignationService.addResignationInfo(resignInfo);
		return new ResponseEntity<String>(addResignationInfo, HttpStatus.OK);
	}

	@GetMapping("/resignationinfo/get/{id}")
	public ResponseEntity<ResignationInfo> getResignationInfo(@PathVariable Integer id) {
		ResignationInfo result = resignationService.getResignationInfo(id);
		return new ResponseEntity<ResignationInfo>(result, HttpStatus.OK);
	}

	@PutMapping("/resignationinfo/update/{id}")
	public ResponseEntity<String> updateResignationInfo(@RequestBody ResignationInfo resignInfo,
			@PathVariable Integer id) {
		String result = resignationService.updateResignationInfo(resignInfo, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
