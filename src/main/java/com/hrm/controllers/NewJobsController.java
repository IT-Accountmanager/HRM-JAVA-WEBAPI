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

import com.hrm.models.NewJobs;
import com.hrm.services.INewJobsService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/general/newjobs")
public class NewJobsController {

	@Autowired
	INewJobsService newJobService;

	@PostMapping("/add")
	public ResponseEntity<String> addNewJob(@RequestBody NewJobs newJob) {
		String result = this.newJobService.addNewJobs(newJob);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<List<NewJobs>> allNewJob() {
		List<NewJobs> allJobs = this.newJobService.allJobs();
		return new ResponseEntity<List<NewJobs>>(allJobs, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<NewJobs> getNewJob(@PathVariable Integer id) {
		NewJobs job = this.newJobService.getJob(id);
		return new ResponseEntity<NewJobs>(job, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> addNewJob(@RequestBody NewJobs newJob, @PathVariable Integer id) {
		String result = this.newJobService.updateJob(newJob, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteNewJob(@PathVariable Integer id) {
		String result = this.newJobService.deleteJob(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
