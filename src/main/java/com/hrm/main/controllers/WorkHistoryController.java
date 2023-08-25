package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.main.models.WorkHistory;
import com.hrm.main.services.IWorkHistoryService;

@RestController
@RequestMapping("/profession")
public class WorkHistoryController {

	@Autowired
	IWorkHistoryService workHistoryService;

	@PostMapping("/workhistory/add")
	public ResponseEntity<String> addWorkInfo(@RequestBody WorkHistory workHistory) {
		String result = workHistoryService.addWorkHistory(workHistory);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
