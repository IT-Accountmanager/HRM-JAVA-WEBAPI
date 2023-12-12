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

import com.hrm.main.models.BasicInfo;
import com.hrm.main.services.IBasicInfoService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/summary")
public class BasicInfoController {

	@Autowired
	IBasicInfoService basicInfoService;

	@PostMapping("/basicinfo/add")
	public ResponseEntity<String> addBasicInfo(@RequestBody BasicInfo basicInfo) {
		String result = basicInfoService.addBasicInfo(basicInfo);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/basicinfo/get/{id}")
	public ResponseEntity<BasicInfo> getBasicInfo(@PathVariable Integer id) {
		BasicInfo result = basicInfoService.getBasicInfo(id);
		return new ResponseEntity<BasicInfo>(result, HttpStatus.OK);
	}

	@PutMapping("/basicinfo/update/{id}")
	public ResponseEntity<String> updateBasicInfo(@RequestBody BasicInfo basicInfo, @PathVariable Integer id) {
		String result = basicInfoService.updateInfo(basicInfo, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
