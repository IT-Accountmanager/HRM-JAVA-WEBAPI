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
import com.hrm.main.models.WorkInfo;
import com.hrm.main.services.IWorkInfoService;
@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

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
	
	@GetMapping("/workinfo/get/{id}")
	public ResponseEntity<WorkInfo> getWorkInfo(@PathVariable Integer id) {
		WorkInfo result = workInfoService.getWorkInfo(id);
		return new ResponseEntity<WorkInfo>(result, HttpStatus.OK);
	}

	@PutMapping("/workinfo/update/{id}")
	public ResponseEntity<String> updateWorkInfo(@RequestBody WorkInfo workInfo, @PathVariable Integer id) {
		String result = workInfoService.updateInfo(workInfo, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
}
