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

import com.hrm.main.models.JVM;
import com.hrm.main.services.IJVMService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/general/jvm")
public class JVMController {

	@Autowired
	IJVMService jvmService;

	@PostMapping("/add")
	public ResponseEntity<String> addJvm(@RequestBody JVM jvm) {
		String result = this.jvmService.addJvm(jvm);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/get")
	public ResponseEntity<List<JVM>> allJvm() {
		List<JVM> allJvm = this.jvmService.allJvm();
		return new ResponseEntity<List<JVM>>(allJvm, HttpStatus.OK);
	}

	@GetMapping("/get/{id}")
	public ResponseEntity<JVM> getJvm(@PathVariable Integer id) {
		JVM jvm = this.jvmService.getJvm(id);
		return new ResponseEntity<JVM>(jvm, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<String> addJvm(@RequestBody JVM jvm, @PathVariable Integer id) {
		String result = this.jvmService.updateJvm(jvm, id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteJvm(@PathVariable Integer id) {
		String result = this.jvmService.deleteJvm(id);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

}
