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

import com.hrm.main.models.BackgroundVerification;
import com.hrm.main.models.HRExecutive;
import com.hrm.main.models.Onboarding;
import com.hrm.main.models.Personal;
import com.hrm.main.models.Helper.EnumCollection.CandidatesStatus;
import com.hrm.main.payloads.HrExecutiveAgreementApprovalDto;
import com.hrm.main.payloads.HrExecutiveBgvSubmissionDto;
import com.hrm.main.payloads.HrExecutiveEducationApprovalDto;
import com.hrm.main.payloads.HrExecutiveFamilyApprovalDto;
import com.hrm.main.payloads.HrExecutivePersonalApprovalDto;
import com.hrm.main.payloads.HrExecutiveWorkApprovalDto;
import com.hrm.main.services.IHRExecutiveService;

@CrossOrigin(origins = { "http://10.10.20.9:8082/", "http://10.10.20.9:8084/", "http://Localhost:4200/" })
@RestController
@RequestMapping("/HRExecutive")

public class HRExecutiveController {

	@Autowired
	IHRExecutiveService hRExecutiveService;

	@PostMapping("/AddExecutive")
	public ResponseEntity<String> createExecutive(@RequestBody HRExecutive executive) {

		String result = this.hRExecutiveService.createExecutive(executive);

		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping("/getAllExecutive/{inReview}/{hrManagerRejected}")
	public ResponseEntity<List<Onboarding>> getAllExecutive(@PathVariable CandidatesStatus inReview,
			@PathVariable CandidatesStatus hrManagerRejected) {
		List<Onboarding> allExecutive = this.hRExecutiveService.getAllExecutive(inReview, hrManagerRejected);

		return new ResponseEntity<List<Onboarding>>(allExecutive, HttpStatus.OK);
	}

	@GetMapping("/getExecutveById/{id}")
	public HRExecutive getExecutiveById(@PathVariable int id) {

		HRExecutive hr = hRExecutiveService.getExecutiveById(id);

		return hr;
	}

	@PutMapping("/updateExecutive/{id}")
	public ResponseEntity<String> updateHRExecutive(@RequestBody HRExecutive executive, @PathVariable Integer id) {

		String result = this.hRExecutiveService.updateHRExecutive(executive, id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/deleteExecutive/{id}")
	public ResponseEntity<String> deleteHrExecutive(@PathVariable Integer id) {

		String result = this.hRExecutiveService.deleteHrExecutive(id);

		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	// 1. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @PostMapping("/transfer") public ResponseEntity<String> checkAndTranfer() {
	 * String personal = this.hRExecutiveService.tranferProfileToHRExecutive();
	 * return new ResponseEntity<String>(personal, HttpStatus.OK); }
	 */

	// 2. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @GetMapping("/getAllCandidate") // @Transactional public List<Onboarding>
	 * getAllOnboarding() {
	 * 
	 * List<Onboarding> result = this.hRExecutiveService.getAllOnboarding(); return
	 * result; }
	 */

	// 3. Transfer ProfileCompleted Candidates in HR-Executive
	/*
	 * @GetMapping("/getCandidates/{status}") public
	 * ResponseEntity<List<Onboarding>> candidates(@PathVariable Status status) {
	 * List<Onboarding> allOnboarding =
	 * this.hRExecutiveService.getAllOnboarding(status); return new
	 * ResponseEntity<List<Onboarding>>(allOnboarding, HttpStatus.OK); }
	 */

	@PostMapping("/postToHrExecutive/{status}")
	public ResponseEntity<Boolean> addCandidatesInHrExecutive(@PathVariable CandidatesStatus status) {
		boolean result = this.hRExecutiveService.postCandidateInHrExecutive(status);
		return new ResponseEntity<Boolean>(result, HttpStatus.OK);
	}

	@PostMapping("personalApproval/{candidateId}")
	public ResponseEntity<HrExecutivePersonalApprovalDto> personalApproval(
			@RequestBody HrExecutivePersonalApprovalDto hrExecutivePersonalApprovalDto,
			@PathVariable long candidateId) {
		HrExecutivePersonalApprovalDto result = this.hRExecutiveService.personalApproval(hrExecutivePersonalApprovalDto,
				candidateId);
		return new ResponseEntity<HrExecutivePersonalApprovalDto>(result, HttpStatus.OK);
	}

	@GetMapping("getPersonalApproval/{candidateId}")
	public ResponseEntity<HrExecutivePersonalApprovalDto> getPersonalApproval(@PathVariable long candidateId) {
		HrExecutivePersonalApprovalDto result = this.hRExecutiveService.getPersonalApproval(candidateId);
		return new ResponseEntity<HrExecutivePersonalApprovalDto>(result, HttpStatus.OK);
	}

	@PostMapping("familyApproval/{candidateId}")
	public ResponseEntity<HrExecutiveFamilyApprovalDto> familyApproval(
			@RequestBody HrExecutiveFamilyApprovalDto hrExecutiveFamilyDto, @PathVariable long candidateId) {
		HrExecutiveFamilyApprovalDto result = this.hRExecutiveService.familyApproval(hrExecutiveFamilyDto, candidateId);
		return new ResponseEntity<HrExecutiveFamilyApprovalDto>(result, HttpStatus.OK);
	}

	@GetMapping("getFamilyApproval/{candidateId}")
	public ResponseEntity<HrExecutiveFamilyApprovalDto> getFamilyApproval(@PathVariable long candidateId) {
		HrExecutiveFamilyApprovalDto result = this.hRExecutiveService.getFamilyApproval(candidateId);
		return new ResponseEntity<HrExecutiveFamilyApprovalDto>(result, HttpStatus.OK);
	}

	@PostMapping("educationApproval/{candidateId}")
	public ResponseEntity<HrExecutiveEducationApprovalDto> educationApproval(
			@RequestBody HrExecutiveEducationApprovalDto hrExecutiveEducationDto, @PathVariable long candidateId) {
		HrExecutiveEducationApprovalDto result = this.hRExecutiveService.educationApproval(hrExecutiveEducationDto,
				candidateId);
		return new ResponseEntity<HrExecutiveEducationApprovalDto>(result, HttpStatus.OK);
	}

	@GetMapping("getEducationApproval/{candidateId}")
	public ResponseEntity<HrExecutiveEducationApprovalDto> getEducationApproval(@PathVariable long candidateId) {
		HrExecutiveEducationApprovalDto result = this.hRExecutiveService.getEducationApproval(candidateId);
		return new ResponseEntity<HrExecutiveEducationApprovalDto>(result, HttpStatus.OK);
	}

	@PostMapping("workApproval/{candidateId}")
	public ResponseEntity<HrExecutiveWorkApprovalDto> workApproval(
			@RequestBody HrExecutiveWorkApprovalDto hrExecutiveWorkDto, @PathVariable long candidateId) {
		HrExecutiveWorkApprovalDto result = this.hRExecutiveService.workApproval(hrExecutiveWorkDto, candidateId);
		return new ResponseEntity<HrExecutiveWorkApprovalDto>(result, HttpStatus.OK);
	}

	@GetMapping("getWorkApproval/{candidateId}")
	public ResponseEntity<HrExecutiveWorkApprovalDto> getWorkApproval(@PathVariable long candidateId) {
		HrExecutiveWorkApprovalDto result = this.hRExecutiveService.getWorkApproval(candidateId);
		return new ResponseEntity<HrExecutiveWorkApprovalDto>(result, HttpStatus.OK);
	}

	@PostMapping("agreementApproval/{candidateId}")
	public ResponseEntity<HrExecutiveAgreementApprovalDto> agreementApproval(
			@RequestBody HrExecutiveAgreementApprovalDto hrExecutiveAgreementApprovalDto,
			@PathVariable long candidateId) {
		HrExecutiveAgreementApprovalDto result = this.hRExecutiveService
				.agreementApproval(hrExecutiveAgreementApprovalDto, candidateId);
		return new ResponseEntity<HrExecutiveAgreementApprovalDto>(result, HttpStatus.OK);
	}

	@GetMapping("getAgreementApproval/{candidateId}")
	public ResponseEntity<HrExecutiveAgreementApprovalDto> getAgreementApproval(@PathVariable long candidateId) {
		HrExecutiveAgreementApprovalDto result = this.hRExecutiveService.getAgreementApproval(candidateId);
		return new ResponseEntity<HrExecutiveAgreementApprovalDto>(result, HttpStatus.OK);
	}

	// ---------------------------Post BGV checkbox--------------------
	@PostMapping("post/bgv/{candidateId}")
	public ResponseEntity<String> postBgv(@RequestBody BackgroundVerification bgv, @PathVariable long candidateId) {
		String result = this.hRExecutiveService.postBgv(bgv, candidateId);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	// ---------------------------Get BGV checkbox--------------------
	@GetMapping("get/bgv/{candidateId}")
	public ResponseEntity<BackgroundVerification> getBgv(@PathVariable long candidateId) {
		BackgroundVerification result = this.hRExecutiveService.getBgv(candidateId);
		return new ResponseEntity<BackgroundVerification>(result, HttpStatus.OK);

	}

	@GetMapping("get/bgvApproval/{candidateId}")
	public ResponseEntity<HrExecutiveBgvSubmissionDto> getBgvApproval(@PathVariable long candidateId) {
		HrExecutiveBgvSubmissionDto result = this.hRExecutiveService.getBgvApproval(candidateId);
		return new ResponseEntity<HrExecutiveBgvSubmissionDto>(result, HttpStatus.OK);
	}

	/*
	 * //----------------------------POST BGV
	 * Approval---------------------------------
	 * 
	 * @PostMapping("bgvApproval/{candidateId}") public
	 * ResponseEntity<HrExecutiveAgreementApprovalDto> agreementApproval(
	 * 
	 * @RequestBody HrExecutiveAgreementApprovalDto hrExecutiveAgreementApprovalDto,
	 * 
	 * @PathVariable long candidateId) { HrExecutiveAgreementApprovalDto result =
	 * this.hRExecutiveService .agreementApproval(hrExecutiveAgreementApprovalDto,
	 * candidateId); return new
	 * ResponseEntity<HrExecutiveAgreementApprovalDto>(result, HttpStatus.OK); }
	 */

	@GetMapping("get/{candidateId}")
	public ResponseEntity<Onboarding> getByCandidateId(@PathVariable long candidateId) {
		Onboarding result = this.hRExecutiveService.getByCandidateId(candidateId);
		return new ResponseEntity<Onboarding>(result, HttpStatus.OK);
	}

	// ------------------For Submit Hr Executive------------------

	@PostMapping("submit/{candiateId}")
	public ResponseEntity<Integer> submitHrExecutive(@PathVariable long candiateId) {
		Integer result = this.hRExecutiveService.submitHrExecutive(candiateId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

	// ------------------For Reject by Hr Executive------------------
	@PostMapping("reject/{candiateId}")
	public ResponseEntity<Integer> rejectHrExecutive(@PathVariable long candiateId) {
		Integer result = this.hRExecutiveService.rejectHrExecutive(candiateId);
		return new ResponseEntity<Integer>(result, HttpStatus.OK);
	}

}
