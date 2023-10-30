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
import com.hrm.main.models.HRManager;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.services.IHRManagerService;

@CrossOrigin(origins = { "http://10.10.100.6:8083/", "http://10.10.100.6:8085/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/HRManager")

public class HRManagerController {

	@Autowired
	IHRManagerService hRManagerService;

	@GetMapping("/Add/{status}")
	public ResponseEntity<Boolean> addCandidatesInHRManager(@PathVariable CandidatesStatus status) {
		boolean result = this.hRManagerService.postCandidateInHrManager(status);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);

	}

	@GetMapping("/getAll/{status}")
	public ResponseEntity<List<Onboarding>> getAll(@PathVariable CandidatesStatus status) {
		List<Onboarding> result = this.hRManagerService.getAllHRManager(status);

		return new ResponseEntity<List<Onboarding>>(result, HttpStatus.OK);
	}

	@GetMapping("/getManagerById/{id}")
	public HRManager getManagerById(@PathVariable int id) {

		HRManager hr = hRManagerService.getHRManager(id);

		return hr;
	}

	@PutMapping("/updateManager/{id}")
	public ResponseEntity<String> updateHRManager(@RequestBody HRManager hrManager, @PathVariable Integer id) {

		String result = this.hRManagerService.updateHRManager(hrManager, id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteManager/{id}")
	public ResponseEntity<String> deleteHRManager(@PathVariable Integer id) {

		String result = this.hRManagerService.deleteHRManager(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@GetMapping("/getSrNo")
	public Long getNextSerialNumberForAdd() {
		return this.hRManagerService.nextValue() + 1;

	}

}
