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

import com.hrm.main.models.OnboardingHRExecutive;
import com.hrm.main.services.IOnboardingHRExecutiveService;
@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Onboarding")

public class OnboardingHRExecutiveController {
	
	@Autowired
	IOnboardingHRExecutiveService onboardingHRExecutiveService;
	
	@PostMapping("/AddExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody OnboardingHRExecutive executive){
		
		String result = this.onboardingHRExecutiveService.createExecutive(executive);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllExecutive")
	public ResponseEntity<List<OnboardingHRExecutive>> getAllExecutive(){
		
		List<OnboardingHRExecutive> allExecutive = this.onboardingHRExecutiveService.getAllExecutive();
		
		return new ResponseEntity<List<OnboardingHRExecutive>>(allExecutive, HttpStatus.OK);
	}
	
	@GetMapping("/getExecutveById/{id}")
	public OnboardingHRExecutive getExecutiveById(@PathVariable int id) {
		
		OnboardingHRExecutive hr = onboardingHRExecutiveService.getExecutiveById(id);
		
		return hr;
	}
	
	@PutMapping("/updateExecutive/{id}")
	public ResponseEntity<String> updateHRExecutive(@RequestBody OnboardingHRExecutive executive, @PathVariable Integer id) {
		
		String result = this.onboardingHRExecutiveService.updateHRExecutive(executive, id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteExecutive/{id}")
	public ResponseEntity<String> deleteHrExecutive (@PathVariable Integer id){
		
		String result = this.onboardingHRExecutiveService.deleteHrExecutive(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	

}
