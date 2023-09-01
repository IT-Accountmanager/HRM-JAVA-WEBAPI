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

import com.hrm.main.models.WorkHistory;
import com.hrm.main.models.WorkInfo;
import com.hrm.main.services.IWorkHistoryService;
@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/profession")
public class WorkHistoryController {

	@Autowired
	IWorkHistoryService workHistoryService;

	@PostMapping("/workhistory/add")
	public ResponseEntity<String> addWorkHistory(@RequestBody WorkHistory workHistory) {
		String result = workHistoryService.addWorkHistory(workHistory);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping("/workhistory/get/{id}")
	public ResponseEntity<WorkHistory> getWorkHistory(@PathVariable Integer id) {
		WorkHistory result = workHistoryService.getWorkHistory(id);
		return new ResponseEntity<WorkHistory>(result, HttpStatus.OK);
	}

	@PutMapping("/workhistory/update/{id}")
	public ResponseEntity<String> updateWorkHistory(@RequestBody WorkHistory workHistory, @PathVariable Integer id) {
		String result = workHistoryService.updateWorkHistory(workHistory, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
