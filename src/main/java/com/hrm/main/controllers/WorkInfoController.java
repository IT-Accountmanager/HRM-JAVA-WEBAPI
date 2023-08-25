package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.WorkInfo;
import com.hrm.main.services.IWorkInfoService;

@RestController
@RequestMapping("/profession")

public class WorkInfoController {
	@Autowired
	IWorkInfoService workInfoService;

	@PostMapping("/workinfo/add")
	public ResponseEntity<String> addWorkInfo(@RequestBody WorkInfo workInfo) {
		String result = workInfoService.addWorkInfo(workInfo);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
