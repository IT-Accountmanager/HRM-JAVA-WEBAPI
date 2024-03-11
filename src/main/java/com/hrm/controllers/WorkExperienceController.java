package com.hrm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hrm.models.Work;
import com.hrm.payloads.WorkStatusResponse;
import com.hrm.services.IWorkService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/work")

public class WorkExperienceController {
	@Autowired
	IWorkService workService;

	/*
	 * @PostMapping("/AddWork/{candidateId}") public ResponseEntity<String>
	 * createWork(@RequestBody(required = false) Work work, @PathVariable long
	 * candidateId) { String result = this.workService.createWork(work,
	 * candidateId); return new ResponseEntity<String>(result, HttpStatus.OK); }
	 */

	@PostMapping("/AddWork/{candidateId}")
	public ResponseEntity<String> createWork(@RequestBody(required = false) Work work, @PathVariable long candidateId) {
		String result;

		if (work != null) {
			result = this.workService.createWorkForExperiencedCandidate(work, candidateId);
		} else {
			result = this.workService.createWorkForFresherCandidate(candidateId);
		}

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/getAll/{candidateId}")
	public ResponseEntity<List<Work>> getAllWork(@PathVariable long candidateId) {
		List<Work> allWork = this.workService.getAllWorkByCandidateId(candidateId);
		return new ResponseEntity<List<Work>>(allWork, HttpStatus.OK);
	}

	@GetMapping("/getWork/{id}")
	public ResponseEntity<Work> getWorkById(@PathVariable Integer id) {
		Work work = this.workService.getWork(id);
		return new ResponseEntity<Work>(work, HttpStatus.OK);
	}

	@PutMapping("/EditWork/{id}")
	public ResponseEntity<String> updateWork(@RequestBody Work work, @PathVariable Integer id) {
		String result = this.workService.updateWork(work, id);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("DeleteWork/{id}")
	public ResponseEntity<String> deleteWork(@PathVariable Integer id) {
		String result = this.workService.deleteWork(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/getDocument/{id}")
	public ResponseEntity<String> getDocument(@PathVariable Integer id) {
		String result = this.workService.getDocument(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/get/status/{candidateId}")
	public ResponseEntity<WorkStatusResponse> getWorkStatusByCandidateId(@PathVariable long candidateId) {
		WorkStatusResponse status = this.workService.getWorkStatusByCandidateId(candidateId);
		return new ResponseEntity<WorkStatusResponse>(status, HttpStatus.OK);
	}

}
