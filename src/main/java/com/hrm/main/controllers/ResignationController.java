package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.ResignationInfo;
import com.hrm.main.services.IResignationInfoService;

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

}
