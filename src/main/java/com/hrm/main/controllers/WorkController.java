package com.hrm.main.controllers;

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

import com.hrm.main.models.Work;
import com.hrm.main.services.IWorkService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/work")

public class WorkController {
	@Autowired
	IWorkService workService;

	@PostMapping("/AddWork")
	public ResponseEntity<String> createWork(@RequestBody Work work) {
		String result = this.workService.createWork(work);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/getWork")
	public ResponseEntity<List<Work>> getAllWork() {
		List<Work> allWork = this.workService.getAllWork();
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

}
