package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.BasicInfo;
import com.hrm.main.services.IBasicInfoService;

@RestController
@RequestMapping("/profession")
public class BasicInfoController {

	@Autowired
	IBasicInfoService basicInfoService;

	@PostMapping("/basicinfo/add")
	public ResponseEntity<String> addBasicInfo(@RequestBody BasicInfo basicInfo) {
		String result = basicInfoService.addBasicInfo(basicInfo);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
