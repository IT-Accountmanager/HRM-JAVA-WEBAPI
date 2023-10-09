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
import com.hrm.main.models.Agreement;
import com.hrm.main.services.IAgreementService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/Agreement")


public class AgreementController {
	
	@Autowired
	IAgreementService agreementService;
	
	@PostMapping("/addAgreement")
	public ResponseEntity<String> createAgreement(@RequestBody Agreement agreement){
		
		String result = this.agreementService.createAgreement(agreement);
		
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping("/getAllAgreement")
	public ResponseEntity<List<Agreement>> getAllAgreement(){
		
		List<Agreement> allAgreement = this.agreementService.getAllAgreement();
		
		return new ResponseEntity<List<Agreement>>(allAgreement, HttpStatus.OK);
	}
	
	@PutMapping("/updateAgreement/{id}")
	public ResponseEntity<String> updateAgreement (@RequestBody Agreement agreement,@PathVariable Integer id) {
		
		String result = this.agreementService.updateAgreement(agreement, id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
	
	@DeleteMapping("/deleteAgreement/{id}")
	public ResponseEntity<String> deleteAgreement(@PathVariable Integer id){
		
		String result = this.agreementService.deleteAgreement(id);
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

}
