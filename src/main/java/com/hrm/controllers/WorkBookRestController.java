package com.hrm.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.payloads.WorkBookSubmission;
import com.hrm.services.IWorkBookService;

@CrossOrigin(origins = "*")

@RestController
@RequestMapping("/workBook")
public class WorkBookRestController {

	@Autowired
	IWorkBookService workBookService;

	private static final Logger logger = LoggerFactory.getLogger(WorkBookRestController.class);

	@PostMapping("/checkWorkBook")
	public ResponseEntity<String> checkWorkBook(@RequestBody WorkBookSubmission workBookSubmission) {
		logger.info("Inside Check Work Book Method with candidate Id ={} ", workBookSubmission.getCandidateId());
		try {
			String result = this.workBookService.checkWorkBook(workBookSubmission);
			return ResponseEntity.ok(result);
		} catch (IllegalArgumentException e) {
			logger.error("Validation error: " + e.getMessage(), e);
			return ResponseEntity.badRequest().body(null);
		} catch (Exception e) {
			logger.error("Error checking workbook submission", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

	}

	@GetMapping("/get/{candidateId}")
	public ResponseEntity<WorkBookSubmission> getWorkBookCheck(@PathVariable Long candidateId) {
		if (candidateId == null) {
			logger.warn("Received null candidateId");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		try {
			WorkBookSubmission workBookCheck = workBookService.getWorkBookCheck(candidateId);
			if (workBookCheck == null) {
				logger.warn("No WorkBookSubmission found for candidateId = {}", candidateId);
				return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			}
			return ResponseEntity.ok(workBookCheck);
		} catch (Exception e) {
			logger.error("Exception occurred while getting WorkBookSubmission for candidateId = {}", candidateId, e);
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	

}
