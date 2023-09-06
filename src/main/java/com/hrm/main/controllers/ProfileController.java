
package com.hrm.main.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.hrm.main.services.IProfileService;

@RestController

@RequestMapping("/Profile")
public class ProfileController {

	@Autowired
	IProfileService profileService;
	

	/*
	 * @GetMapping("/get/{id}/{status}") public ResponseEntity<String>
	 * changeStatus(@PathVariable Integer id, @PathVariable String status) { String
	 * result = this.profileService.changeStatus(id, status); return new
	 * ResponseEntity<String>(result, HttpStatus.OK); }
	 */

	/*
	 * @GetMapping("get") public ResponseEntity<List<Onboarding>> get()
	 */
	
}
