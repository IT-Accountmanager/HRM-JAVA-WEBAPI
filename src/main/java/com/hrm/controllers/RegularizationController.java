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

import com.hrm.models.Regularization;
import com.hrm.services.IRegularizationService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })

@RestController
@RequestMapping("/attendance")
public class RegularizationController {
	@Autowired
	IRegularizationService regularizationService;

	@PostMapping("/regularization/post")
	public ResponseEntity<String> addRegularization(@RequestBody Regularization regularization) {
		String result = this.regularizationService.addRegularization(regularization);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	

	@GetMapping("/regularization/get")
	public ResponseEntity<List<Regularization>> getAllRegularization() {
		List<Regularization> allRegularization = this.regularizationService.allRegularization();
		return new ResponseEntity<List<Regularization>>(allRegularization, HttpStatus.OK);
	}

	@PutMapping("/regularization/edit/{id}")
	public ResponseEntity<String> editRegularization(@RequestBody Regularization regularization,
			@PathVariable Integer id) {
		String result = this.regularizationService.editRegularization(regularization, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("/regularization/delete/{id}")
	public ResponseEntity<String> deleteRegularization(@PathVariable Integer id) {
		String result = this.regularizationService.deleteRegularization(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/regularization/get/{id}")
	public ResponseEntity<Regularization> getRegularization(@PathVariable Integer id) {
		Regularization result = this.regularizationService.getRegularization(id);
		return new ResponseEntity<Regularization>(result, HttpStatus.OK);
	}

}
